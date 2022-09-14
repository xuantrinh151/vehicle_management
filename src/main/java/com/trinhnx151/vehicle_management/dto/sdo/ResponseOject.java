package com.trinhnx151.vehicle_management.dto.sdo;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseOject {
    private String status;
    private String message;
    private Object data;
}