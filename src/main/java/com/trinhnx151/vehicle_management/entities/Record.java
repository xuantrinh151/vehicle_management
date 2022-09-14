package com.trinhnx151.vehicle_management.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long vehicleId;
    private String violationLocation;
    private String contentViolation;
    private Boolean pull;
    private Double fineAmount;
    private Boolean paymentStatus;
    private Integer status;

    public interface Status {
        Integer CEASE_USING = 0;
        Integer IN_USE = 1;
        Integer DELETED = 2;
    }
}
