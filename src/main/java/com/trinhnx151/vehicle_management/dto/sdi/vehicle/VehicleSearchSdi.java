package com.trinhnx151.vehicle_management.dto.sdi.vehicle;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VehicleSearchSdi {
    private String keyword;
}
