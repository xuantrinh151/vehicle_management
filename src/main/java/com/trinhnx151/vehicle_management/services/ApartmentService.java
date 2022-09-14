package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSelfSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentUpdateSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApartmentService {
    Page<ApartmentSearchSdo> search(ApartmentSearchSdi request, Pageable pageable);

    ApartmentSelfSdo findById(Long id);

    ApartmentCreateSdo create(ApartmentCreateSdi request);

    ApartmentUpdateSdo update(ApartmentUpdateSdi request);

    Boolean deleteById(Long id);
}
