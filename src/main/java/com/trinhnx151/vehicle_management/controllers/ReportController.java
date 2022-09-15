package com.trinhnx151.vehicle_management.controllers;

import com.trinhnx151.vehicle_management.dto.sdo.report.VehicleReportSdo;
import com.trinhnx151.vehicle_management.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/report")
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/violations")
    Page<VehicleReportSdo> listMostViolations(Pageable pageable) {
        return reportService.listMostViolations(pageable);
    }

}
