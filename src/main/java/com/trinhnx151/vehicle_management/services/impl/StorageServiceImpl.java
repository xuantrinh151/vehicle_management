package com.trinhnx151.vehicle_management.services.impl;

import com.trinhnx151.vehicle_management.dto.sdi.Image.ImageUploadSdi;
import com.trinhnx151.vehicle_management.dto.sdo.image.ImageUploadSdo;
import com.trinhnx151.vehicle_management.exception.custom.ImageErrorException;
import com.trinhnx151.vehicle_management.services.StorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class StorageServiceImpl implements StorageService {

    private final Path storageFolder = Paths.get("uploads");

    @Override
    public String storeFile(MultipartFile file, HttpSession session) {
        return null;
    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }

    @Override
    public byte[] readFileContent(String fileName) {
        try {
            Path file = storageFolder.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return StreamUtils.copyToByteArray(resource.getInputStream());
            } else {
                throw new ImageErrorException(
                        "Could not read file: " + fileName);
            }
        } catch (IOException exception) {
            throw new ImageErrorException("Could not read file: " + fileName);
        }
    }

    @Override
    public ArrayList<ImageUploadSdo> upload(ArrayList<ImageUploadSdi> imageUploadSdi) {
        ArrayList<ImageUploadSdo> urlList = new ArrayList<>();
        try {
            for (ImageUploadSdi image :
                    imageUploadSdi) {
                byte[] imageByte = Base64.getDecoder().decode(image.getBase64());
                if (!isImageFile(image)) {
                    throw new ImageErrorException("Chỉ được upload ảnh");
                }
                String generatedFileName = UUID.randomUUID().toString().replace("-", "");
                generatedFileName = generatedFileName + image.getName().substring(image.getName().lastIndexOf("."));
                Path destinationFilePath = this.storageFolder.resolve(
                                Paths.get(generatedFileName))
                        .normalize().toAbsolutePath();
                FileOutputStream fileOutputStream = new FileOutputStream(destinationFilePath.toString());
                fileOutputStream.write(imageByte);
                fileOutputStream.close();
                urlList.add(ImageUploadSdo.builder().name(generatedFileName).url("http://localhost:8080/api/v1/record/images/" + generatedFileName).build());
            }
            return urlList;
        } catch (Exception e) {
            throw new ImageErrorException("Đã xảy ra lỗi trong khi upload");
        }
    }

    private boolean isImageFile(ImageUploadSdi imageUploadSdi) {
        String fileExtension = imageUploadSdi.getName().substring(imageUploadSdi.getName().lastIndexOf(".") + 1);
        return Arrays.asList(new String[]{"png", "jpg", "jpeg", "bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }
}
