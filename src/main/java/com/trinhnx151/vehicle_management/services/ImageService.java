package com.trinhnx151.vehicle_management.services;

import com.trinhnx151.vehicle_management.dto.sdo.image.ImageUploadSdo;
import com.trinhnx151.vehicle_management.entities.Image;

import java.util.ArrayList;
import java.util.List;

public interface ImageService {
    List<Image> getImageByRecordId(Long id);

    void create (ArrayList<ImageUploadSdo> imageList,Long recordId);

    void deleteByRecordId(Long id);
}
