package com.trinhnx151.vehicle_management.services.impl;

import com.trinhnx151.vehicle_management.dto.sdo.report.VehicleReportSdo;
import com.trinhnx151.vehicle_management.repositories.ReportRepo;
import com.trinhnx151.vehicle_management.services.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepo reportRepo;

    @Override
    public Page<VehicleReportSdo> listMostViolations(Pageable pageable) {
        return reportRepo.listMostViolations(pageable);
    }
}
