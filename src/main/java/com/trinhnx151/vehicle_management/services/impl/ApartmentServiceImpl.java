package com.trinhnx151.vehicle_management.services.impl;

import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSelfSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentUpdateSdo;
import com.trinhnx151.vehicle_management.entities.Apartment;
import com.trinhnx151.vehicle_management.exception.custom.DuplicateException;
import com.trinhnx151.vehicle_management.exception.custom.NotFoundException;
import com.trinhnx151.vehicle_management.repositories.ApartmentRepo;
import com.trinhnx151.vehicle_management.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepo apartmentRepo;

    @Override
    public Page<ApartmentSearchSdo> search(ApartmentSearchSdi request, Pageable pageable) {
        return apartmentRepo.search(request,pageable);
    }

    @Override
    public ApartmentSelfSdo findById(Long id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if(apartment.isPresent()){
            return ApartmentSelfSdo.builder()
                    .id(apartment.get().getId())
                    .code(apartment.get().getCode())
                    .hostName(apartment.get().getHostName())
                    .dob(apartment.get().getDob())
                    .email(apartment.get().getEmail())
                    .phone(apartment.get().getPhone())
                    .totalUnpaidFines(apartment.get().getTotalUnpaidFines())
                    .status(apartment.get().getStatus())
                    .build();
        }
        else {
            throw new NotFoundException("Không tìm thấy căn hộ với id [" + id +"]");
        }
    }

    @Override
    public ApartmentCreateSdo create(ApartmentCreateSdi request) {
        this.validateCreate(request);
        Apartment apartment = request.toApartment();
        apartmentRepo.save(apartment);
        return ApartmentCreateSdo.builder().id(apartment.getId()).build();
    }

    @Override
    public ApartmentUpdateSdo update(ApartmentUpdateSdi request) {
        this.validateUpdate(request);
        Apartment apartment = request.toApartment();
        apartmentRepo.save(apartment);
        return ApartmentUpdateSdo.builder()
                .id(apartment.getId())
                .code(apartment.getCode())
                .hostName(apartment.getHostName())
                .dob(apartment.getDob())
                .phone(apartment.getPhone())
                .email(apartment.getEmail())
                .totalUnpaidFines(apartment.getTotalUnpaidFines())
                .status(apartment.getStatus()).build();
    }

    @Override
    public Boolean deleteById(Long id) {
        Optional<Apartment> apartment = apartmentRepo.findById(id);
        if (apartment.isPresent()) {
            apartment.get().setStatus(2);
            apartmentRepo.save(apartment.get());
            return true;
        }
        return false;
    }

    private void validateUpdate(ApartmentUpdateSdi request) {
        Long id = request.getId();
        String code = request.getCode().toUpperCase();
        Optional<Apartment> apartmentById = apartmentRepo.findByIdWithAllStatus(id);
        if(apartmentById.isEmpty()){
            throw new NotFoundException("Không tồn tại căn hộ với id ["+ id +"]");
        }
        Optional<Apartment> apartmentByCode = apartmentRepo.findByCode(code,id);
        if(apartmentByCode.isPresent()){
            throw new DuplicateException("Mã căn hộ ["+code+"] đã tồn tại ");
        }
    }

    private void validateCreate(ApartmentCreateSdi request) {
        String code = request.getCode().toUpperCase();
        Optional<Apartment> apartmentByCode = apartmentRepo.findByCode(code);
        if(apartmentByCode.isPresent()){
            throw new DuplicateException(("Mã căn hộ [" + code + "] đã tồn tại"));
        }
    }
}
