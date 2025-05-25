package com.faysal.Jobkhujibd_backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageServiceImpl implements FileStorageService {

    @Value("${file.upload-dir:uploads}") // fallback default uploads directory
    private String uploadDir;

    @Override
    public String store(MultipartFile file) {
        try {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path storageLocation = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(storageLocation);

            Path targetLocation = storageLocation.resolve(filename);
            file.transferTo(targetLocation.toFile());

            return filename;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file. Error: " + ex.getMessage(), ex);
        }
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            File file = filePath.toFile();
            if (!file.exists() || !file.canRead()) {
                return null;
            }
            return new FileSystemResource(file);
        } catch (Exception ex) {
            throw new RuntimeException("Could not load file. Error: " + ex.getMessage(), ex);
        }
    }
}
