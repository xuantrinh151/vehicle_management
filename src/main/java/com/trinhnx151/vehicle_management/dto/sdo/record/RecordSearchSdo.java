package com.trinhnx151.vehicle_management.dto.sdo.record;

import com.trinhnx151.vehicle_management.entities.Image;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecordSearchSdo {
    private Long id;
    private String licensePlates;
    private String violationLocation;
    private String contentViolation;
    private List<Image> imageList;
    private Boolean pull;
    private Double fineAmount;
    private Boolean paymentStatus;
    private Integer status;
}
