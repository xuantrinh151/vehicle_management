package com.trinhnx151.vehicle_management.services;


import com.trinhnx151.vehicle_management.dto.sdi.Image.ImageUploadSdi;
import com.trinhnx151.vehicle_management.dto.sdo.image.ImageUploadSdo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.stream.Stream;

public interface StorageService {
    String storeFile(MultipartFile file, HttpSession session);

    Stream<Path> loadAll(); //load all file inside a folder

    byte[] readFileContent(String fileName);

    ArrayList<ImageUploadSdo> upload(ArrayList<ImageUploadSdi> imageUploadSdi);
}
