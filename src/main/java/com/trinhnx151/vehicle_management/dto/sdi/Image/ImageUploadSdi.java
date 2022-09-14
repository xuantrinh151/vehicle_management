package com.trinhnx151.vehicle_management.dto.sdi.Image;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageUploadSdi {
    private String base64;
    private String name;
}
