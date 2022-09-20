package com.trinhnx151.vehicle_management.services.impl;

import java.util.Objects;
import java.util.Optional;
import java.util.ArrayList;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.trinhnx151.vehicle_management.services.*;
import com.trinhnx151.vehicle_management.dto.sdi.Email;
import com.trinhnx151.vehicle_management.entities.Image;
import com.trinhnx151.vehicle_management.entities.Record;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import com.trinhnx151.vehicle_management.entities.Apartment;
import com.trinhnx151.vehicle_management.repositories.ImageRepo;
import com.trinhnx151.vehicle_management.repositories.RecordRepo;
import com.trinhnx151.vehicle_management.repositories.VehicleRepo;
import com.trinhnx151.vehicle_management.repositories.ApartmentRepo;
import com.trinhnx151.vehicle_management.dto.sdi.Image.ImageUploadSdi;
import com.trinhnx151.vehicle_management.dto.sdo.image.ImageUploadSdo;
import com.trinhnx151.vehicle_management.dto.sdi.record.RecordCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.record.RecordSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.record.RecordUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordUpdateSdo;
import com.trinhnx151.vehicle_management.exception.custom.ValidException;
import com.trinhnx151.vehicle_management.exception.custom.NotFoundException;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepo recordRepo;
    private final ImageService imageService;
    private final ApartmentRepo apartmentRepo;
    private final StorageService storageService;
    private final VehicleService vehicleService;
    private final EmailService emailService;
    private final VehicleRepo vehicleRepo;
    private final ImageRepo imageRepo;

    @Override
    public Page<RecordSearchSdo> search(RecordSearchSdi request, Pageable pageable) {
        return recordRepo.search(request, pageable);
    }

    @Override
    public RecordCreateSdo create(RecordCreateSdi request) {
        this.validateCreate(request);
        String licensePlates = request.getVehicle().getLicensePlates();
        Record record = request.toRecord();
        Optional<Vehicle> vehicle;
        if (request.getPull()) {
            record.setFineAmount(2000.0);
        } else {
            record.setFineAmount(500.0);
        }
        vehicle = vehicleService.findByLicensePlates(licensePlates);
        vehicle.ifPresent(value -> record.setVehicleId(value.getId()));
        if (vehicle.isEmpty()) {
            //create vehicle with apartment unknown
            Apartment apartment = new Apartment();
            apartment.setCode("Unknown");
            apartment.setHostName("Unknown");
            apartment.setEmail("Unknown");
            apartment.setPhone("Unknown");
            apartment.setTotalUnpaidFines(0);
            apartment.setStatus(1);
            apartmentRepo.save(apartment);
            request.getVehicle().setApartmentId(apartment.getId());
            Long vehicleId = vehicleService.create(request.getVehicle()).getId();
            vehicle = vehicleRepo.findById(vehicleId);
            record.setVehicleId(vehicleId);
        }
        recordRepo.save(record);

        //update unpaidFines when create record

        if (vehicle.isPresent() && !record.getPaymentStatus()) {
            Optional<Apartment> apartment = apartmentRepo.findById(vehicle.get().getApartmentId());
            if (apartment.isPresent()) {
                apartment.get().setTotalUnpaidFines(apartment.get().getTotalUnpaidFines() + record.getFineAmount());
                apartmentRepo.save(apartment.get());
            }
        }

        //create images
        ArrayList<ImageUploadSdi> imageUploadSdiList = request.getImageList();
        ArrayList<ImageUploadSdo> imageList = storageService.upload(imageUploadSdiList);
        imageService.create(imageList, record.getId());

        //get list url of image
        ArrayList<Image> images = imageRepo.findAllByRecordId(record.getId());
        StringBuilder urlList = new StringBuilder();
        int count = 1;
        for (Image i :
                images) {
            urlList.append("Ảnh ").append(count++).append(" : ").append(i.getUrl()).append("\n");
        }

        //send mail
        if (vehicle.isPresent()) {
            Optional<Apartment> apartment = apartmentRepo.findById(vehicle.get().getApartmentId());
            if (apartment.isPresent()) {
                if (!apartment.get().getHostName().equals("Unknown")) {
                    Email email = Email.builder()
                            .recipient(apartment.get().getEmail())
                            .msgBody("Số tiền bạn cần nộp là " + record.getFineAmount() + "\n\nĐây là hình ảnh vi phạm của bạn\n" + urlList.toString())
                            .subject("Báo cáo vi phạm với xe: " + vehicle.get().getLicensePlates())
                            .build();
                    emailService.sendSimpleMail(email);
                }
            }
        }
        return RecordCreateSdo.builder().id(record.getId()).build();
    }

    @Override
    public RecordUpdateSdo update(RecordUpdateSdi request) {
        this.validateUpdate(request);
        Record record = request.toRecord();
        if (request.getPull()) {
            record.setFineAmount(2000.0);
        } else {
            record.setFineAmount(500.0);
        }

        Long oldVehicleId = recordRepo.findById(request.getId()).get().getVehicleId();
        Long newVehicleId = request.getVehicleId();
        recordRepo.save(record);

        Optional<Vehicle> oldVehicle = vehicleRepo.findById(oldVehicleId);
        Optional<Vehicle> newVehicle = vehicleRepo.findById(newVehicleId);

        //update unpaidFines when update record
        oldVehicle.ifPresent(vehicle -> updateTotalUnpaidFines(vehicle.getApartmentId()));
        newVehicle.ifPresent(vehicle -> updateTotalUnpaidFines(vehicle.getApartmentId()));

        //update images
        imageService.deleteByRecordId(record.getId());
        ArrayList<ImageUploadSdi> imageUploadSdiList = request.getImageList();
        ArrayList<ImageUploadSdo> imageList = storageService.upload(imageUploadSdiList);
        imageService.create(imageList, record.getId());

        //get list url of image
        ArrayList<Image> images = imageRepo.findAllByRecordId(record.getId());
        StringBuilder urlList = new StringBuilder();
        int count = 1;
        for (Image i :
                images) {
            urlList.append("Ảnh ").append(count++).append(" : ").append(i.getUrl()).append("\n");
        }

        //send mail
        if (newVehicle.isPresent()) {
            Optional<Apartment> newApartment = apartmentRepo.findById(newVehicle.get().getApartmentId());
            if (newApartment.isPresent()) {
                if (!newApartment.get().getHostName().equals("Unknown")) {
                    Email email = Email.builder()
                            .recipient(newApartment.get().getEmail())
                            .msgBody("Số tiền bạn cần nộp là " + record.getFineAmount() + "\n\nĐây là hình ảnh vi phạm của bạn\n" + urlList.toString())
                            .subject("Báo cáo vi phạm với xe (Đã Update): " + newVehicle.get().getLicensePlates())
                            .build();
                    emailService.sendSimpleMail(email);
                }
            }
        }
        return RecordUpdateSdo.builder().id(record.getId()).build();
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Record> record = recordRepo.findById(id);
        if (record.isPresent()) {
            record.get().setStatus(2);
            recordRepo.save(record.get());
            return true;
        }
        return false;
    }

    private void validateCreate(RecordCreateSdi request) {
        String licensePlates = request.getVehicle().getLicensePlates();
        if (licensePlates.isEmpty()) {
            throw new ValidException("Biển số xe không được để trống");
        }
    }

    private void validateUpdate(RecordUpdateSdi request) {
        Long id = request.getId();
        Optional<Record> record = recordRepo.findById(id);
        if (record.isEmpty()) {
            throw new NotFoundException("Không tìm thấy bản vi phạm với id: " + id);
        }
        Long vehicleId = request.getVehicleId();
        Optional<Vehicle> vehicle = vehicleRepo.findByIdWithAllStatus(vehicleId);
        if (vehicle.isEmpty()) {
            throw new NotFoundException("Không tìm thấy phương tiện với id: " + id);
        }
    }

    private void updateTotalUnpaidFines(Long id) {
        Double totalUnpaidFines = apartmentRepo.getTotalUnpaidFines(id);
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if (apartment.isPresent()) {
            apartment.get().setTotalUnpaidFines(totalUnpaidFines);
            apartmentRepo.save(apartment.get());
        }
    }
}
