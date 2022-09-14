package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleUpdateSdo;
import com.trinhnx151.vehicle_management.entities.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface VehicleService {
    Page<VehicleSearchSdo> search(VehicleSearchSdi request, Pageable pageable);

    VehicleCreateSdo create(VehicleCreateSdi request);

    VehicleUpdateSdo update(VehicleUpdateSdi request);

    Boolean deleteById(Long id);

   Optional<Vehicle> findByLicensePlates(String licensePlates);
}
