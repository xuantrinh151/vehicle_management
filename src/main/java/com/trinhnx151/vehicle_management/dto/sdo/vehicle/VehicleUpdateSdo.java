package com.trinhnx151.vehicle_management.dto.sdo.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleUpdateSdo {
    private Long id;
    private String licensePlates;
    private String vehicleCompany;
    private String typeVehicle;
    private String apartmentCode;
    private Integer status;
}
