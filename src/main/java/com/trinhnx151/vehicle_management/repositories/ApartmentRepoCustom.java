package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSearchSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ApartmentRepoCustom {

    Page<ApartmentSearchSdo> search(ApartmentSearchSdi request, Pageable pageable);


}
