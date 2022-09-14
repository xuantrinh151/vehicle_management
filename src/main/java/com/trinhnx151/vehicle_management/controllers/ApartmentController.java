package com.trinhnx151.vehicle_management.controllers;

import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.apartment.ApartmentUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentSelfSdo;
import com.trinhnx151.vehicle_management.dto.sdo.apartment.ApartmentUpdateSdo;
import com.trinhnx151.vehicle_management.services.ApartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/apartment")
public class ApartmentController {
    private final ApartmentService apartmentService;

    @GetMapping("/search")
    Page<ApartmentSearchSdo> search(ApartmentSearchSdi request, Pageable pageable) {
        return apartmentService.search(request, pageable);
    }

    @GetMapping("/{id}")
    ApartmentSelfSdo findById(@PathVariable Long id){
        return apartmentService.findById(id);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    ApartmentCreateSdo create(@RequestBody @Valid ApartmentCreateSdi request) {
        return apartmentService.create(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    ApartmentUpdateSdo update(@RequestBody @Valid ApartmentUpdateSdi request){
        return apartmentService.update(request);
    }

    @DeleteMapping("/{id}/delete")
    Boolean delete(@PathVariable Long id) {
        return apartmentService.deleteById(id);
    }
}
