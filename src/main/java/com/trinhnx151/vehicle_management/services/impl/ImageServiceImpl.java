package com.trinhnx151.vehicle_management.services.impl;

import com.trinhnx151.vehicle_management.dto.sdo.image.ImageUploadSdo;
import com.trinhnx151.vehicle_management.entities.Image;
import com.trinhnx151.vehicle_management.repositories.ImageRepo;
import com.trinhnx151.vehicle_management.services.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepo imageRepo;

    @Override
    public List<Image> getImageByRecordId(Long id) {
        return imageRepo.findAllByRecordId(id);
    }

    @Override
    public void create(ArrayList<ImageUploadSdo> imageList, Long recordId) {
        for (ImageUploadSdo i :
                imageList) {
            Image image = new Image();
            image.setName(i.getName());
            image.setRecordId(recordId);
            image.setUrl(i.getUrl());
            image.setStatus(1);
            imageRepo.save(image);
        }
    }

    @Override
    public void deleteByRecordId(Long id) {
        imageRepo.deleteByRecordId(id);
    }
}
