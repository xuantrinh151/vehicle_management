package com.trinhnx151.vehicle_management.dto.sdo.apartment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentSelfSdo {
    private Long id;
    private String code;
    private String hostName;
    private Timestamp dob;
    private String phone;
    private String email;
    private double totalUnpaidFines;
    private Integer status;
}
