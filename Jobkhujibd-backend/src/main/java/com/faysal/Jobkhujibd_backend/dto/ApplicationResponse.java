package com.faysal.Jobkhujibd_backend.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ApplicationResponse {
    private Long id;
    private String companyId;
    private String fullName;
    private String email;
    private Date appliedAt;
    private String resumeDownloadUrl;
}