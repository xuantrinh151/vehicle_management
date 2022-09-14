package com.trinhnx151.vehicle_management.dto.sdi.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordSearchSdi {
    private String keyword;
    private String paymentStatus;
    private String pull;
}
