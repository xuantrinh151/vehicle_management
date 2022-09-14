package com.trinhnx151.vehicle_management.controllers;

import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.vehicle.VehicleUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.vehicle.VehicleUpdateSdo;
import com.trinhnx151.vehicle_management.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/v1/vehicle")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping("/search")
    Page<VehicleSearchSdo> search(VehicleSearchSdi request, Pageable pageable) {
        return vehicleService.search(request, pageable);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    VehicleCreateSdo create(@RequestBody @Valid VehicleCreateSdi request) {
        return vehicleService.create(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    VehicleUpdateSdo update(@RequestBody @Valid VehicleUpdateSdi request) {
        return vehicleService.update(request);
    }

    @DeleteMapping("/{id}/delete")
    Boolean delete(@PathVariable Long id) {
        return vehicleService.deleteById(id);
    }
}
