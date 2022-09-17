package com.trinhnx151.vehicle_management.dto.sdo.report;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleReportSdo {

    private String licensePlates;
    private String vehicleCompany;
    private String typeVehicle;
    private String apartmentCode;
    private int total;
    private Integer status;
}
