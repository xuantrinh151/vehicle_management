package com.trinhnx151.vehicle_management.dto.sdo.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleSearchSdo {
    private Long id;
    private Long apartmentId;
    private String licensePlates;
    private String vehicleCompany;
    private String typeVehicle;
    private Integer status;


}
