package com.trinhnx151.vehicle_management.entities;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String hostName;
    private Timestamp dob;
    private String phone;
    private String email;
    private double totalUnpaidFines;
    private Integer status;

    public interface Status {
        Integer CEASE_USING = 0;
        Integer IN_USE = 1;
        Integer DELETED = 2;
    }


}

