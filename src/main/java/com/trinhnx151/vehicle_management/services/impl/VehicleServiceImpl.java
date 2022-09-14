package com.trinhnx151.vehicle_management.services.impl;

import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleUpdateSdo;
import com.trinhnx151.vehicle_management.entities.Apartment;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import com.trinhnx151.vehicle_management.exception.custom.DuplicateException;
import com.trinhnx151.vehicle_management.exception.custom.NotFoundException;
import com.trinhnx151.vehicle_management.repositories.ApartmentRepo;
import com.trinhnx151.vehicle_management.repositories.VehicleRepo;
import com.trinhnx151.vehicle_management.services.ApartmentService;
import com.trinhnx151.vehicle_management.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepo vehicleRepo;
    private final ApartmentRepo apartmentRepo;
    private final ApartmentService apartmentService;

    @Override
    public Page<VehicleSearchSdo> search(VehicleSearchSdi request, Pageable pageable) {
        return vehicleRepo.search(request, pageable);
    }

    @Override
    public VehicleCreateSdo create(VehicleCreateSdi request) {
        this.validateCreate(request);
        Vehicle vehicle = request.toVehicle();
        Long apartmentId = request.getApartmentId();
        Optional<Apartment> apartment = apartmentRepo.findById(apartmentId);
        apartment.ifPresent(value -> vehicle.setApartmentId(value.getId()));
        vehicleRepo.save(vehicle);
        return VehicleCreateSdo.builder().id(vehicle.getId()).build();
    }

    @Override
    public VehicleUpdateSdo update(VehicleUpdateSdi request) {
        this.validateUpdate(request);
        Vehicle vehicle = request.toVehicle();
        String apartmentCode = request.getApartmentCode();
        Optional<Apartment> apartment = apartmentRepo.findByCode(apartmentCode);
        if (!apartmentCode.isEmpty()) {
            apartment.ifPresent(value -> vehicle.setApartmentId(value.getId()));
        }
        vehicleRepo.save(vehicle);
        return VehicleUpdateSdo.builder()
                .id(vehicle.getId())
                .licensePlates(vehicle.getLicensePlates())
                .vehicleCompany(vehicle.getVehicleCompany())
                .typeVehicle(vehicle.getTypeVehicle())
                .status(vehicle.getStatus())
                .apartmentCode(apartmentCode)
                .build();
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Vehicle> vehicle = vehicleRepo.findById(id);
        if (vehicle.isPresent()) {
            vehicle.get().setStatus(2);
            vehicleRepo.save(vehicle.get());
            return true;
        }
        return false;
    }

    @Override
    public Optional<Vehicle> findByLicensePlates(String licensePlates) {
        return vehicleRepo.findByLicensePlates(licensePlates);
    }

    private void validateUpdate(VehicleUpdateSdi request) {
        Long id = request.getId();
        String licensePlates = request.getLicensePlates().toUpperCase();
        String apartmentCode = request.getApartmentCode().toUpperCase();
        Optional<Vehicle> vehicleById = vehicleRepo.findByIdWithAllStatus(id);
        if (vehicleById.isEmpty()) {
            throw new NotFoundException(("Phương tiện với [" + id + "] không tồn tại"));
        }
        if (!apartmentCode.isEmpty()) {
            Optional<Apartment> apartmentByCode = apartmentRepo.findByCode(apartmentCode);
            if (apartmentByCode.isEmpty()) {
                throw new NotFoundException(("Mã căn hộ [" + apartmentCode + "] không tồn tại"));
            }
        }
        Optional<Vehicle> vehicleByLicensePlates = vehicleRepo.findByLicensePlates(licensePlates, id);
        if (vehicleByLicensePlates.isPresent()) {
            throw new NotFoundException(("Biển số xe [" + licensePlates + "] đã tồn tại"));
        }
    }

    private void validateCreate(VehicleCreateSdi request) {
        String licensePlates = request.getLicensePlates().toUpperCase();
        Long apartmentId = request.getApartmentId();

        Optional<Vehicle> vehicleByLicensePlates = vehicleRepo.findByLicensePlates(licensePlates);
        if (vehicleByLicensePlates.isPresent()) {
            throw new DuplicateException(("Biển số xe [" + licensePlates + "] đã tồn tại"));
        }

        Optional<Apartment> apartment = apartmentRepo.findById(apartmentId);
        if (apartment.isEmpty()) {
            throw new NotFoundException(("Id căn hộ [" + apartmentId + "] không tồn tại"));
        }

    }
}
