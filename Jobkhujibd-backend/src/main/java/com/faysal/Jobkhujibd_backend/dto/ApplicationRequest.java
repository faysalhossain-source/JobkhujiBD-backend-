package com.faysal.Jobkhujibd_backend.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ApplicationRequest {
    private String companyId;
    private String fullName;
    private String email;
    private MultipartFile resumePath;
}