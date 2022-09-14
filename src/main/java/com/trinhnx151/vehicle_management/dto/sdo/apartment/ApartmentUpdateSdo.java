package com.trinhnx151.vehicle_management.dto.sdo.apartment;

import com.sun.istack.NotNull;
import com.trinhnx151.vehicle_management.entities.Apartment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApartmentUpdateSdo {
    private Long id;
    private String code;
    private String hostName;
    private Timestamp dob;
    private String phone;
    private String email;
    private double totalUnpaidFines;
    private Integer status;
}
