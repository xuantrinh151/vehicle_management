package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.dto.sdo.report.VehicleReportSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReportService {
    Page<VehicleReportSdo> listMostViolations(Pageable pageable);
}
