package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleSearchSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface VehicleRepoCustom {
    Page<VehicleSearchSdo> search(VehicleSearchSdi request, Pageable pageable);
}
