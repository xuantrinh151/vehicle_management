package com.trinhnx151.vehicle_management.repositories;

import com.trinhnx151.vehicle_management.dto.sdo.report.VehicleReportSdo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepoCustom {

    Page<VehicleReportSdo> listMostViolations(Pageable pageable);
}
