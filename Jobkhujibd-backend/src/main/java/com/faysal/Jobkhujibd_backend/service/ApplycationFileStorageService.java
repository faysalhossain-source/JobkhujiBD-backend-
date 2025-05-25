package com.faysal.Jobkhujibd_backend.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface ApplycationFileStorageService {
    void init();
    String store(MultipartFile file);
    Resource loadAsResource(String filename);
    void deleteAll();
    Stream<Path> loadAll();
}