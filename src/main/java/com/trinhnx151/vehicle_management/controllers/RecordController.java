package com.trinhnx151.vehicle_management.controllers;

import com.trinhnx151.vehicle_management.dto.sdi.record.RecordCreateSdi;
import com.trinhnx151.vehicle_management.dto.sdi.record.RecordSearchSdi;
import com.trinhnx151.vehicle_management.dto.sdi.record.RecordUpdateSdi;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordCreateSdo;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordSearchSdo;
import com.trinhnx151.vehicle_management.dto.sdo.record.RecordUpdateSdo;
import com.trinhnx151.vehicle_management.services.RecordService;
import com.trinhnx151.vehicle_management.services.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/record")
public class RecordController {
    private final RecordService recordService;
    private final StorageService storageService;

    @GetMapping("/search")
    Page<RecordSearchSdo> search(RecordSearchSdi request, Pageable pageable) {
        return recordService.search(request, pageable);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    RecordCreateSdo create(@RequestBody @Valid RecordCreateSdi request) {
        return recordService.create(request);
    }

    @PutMapping("/update")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    RecordUpdateSdo update(@RequestBody @Valid RecordUpdateSdi request) {
        return recordService.update(request);
    }

    @DeleteMapping("/{id}/delete")
    Boolean delete(@PathVariable Long id) {
        return recordService.deleteById(id);
    }

    @GetMapping("images/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        } catch (Exception ex){
            return ResponseEntity.noContent().build();
        }
    }
}
