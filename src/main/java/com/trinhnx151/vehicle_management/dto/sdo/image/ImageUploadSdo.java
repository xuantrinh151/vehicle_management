package com.trinhnx151.vehicle_management.dto.sdo.image;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImageUploadSdo {
    private String name;
    private String url;
}
