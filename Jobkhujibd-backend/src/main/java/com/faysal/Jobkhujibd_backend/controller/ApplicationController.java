package com.faysal.Jobkhujibd_backend.controller;

import com.faysal.Jobkhujibd_backend.dto.ApplicationRequest;
import com.faysal.Jobkhujibd_backend.dto.ApplicationResponse;
import com.faysal.Jobkhujibd_backend.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApplicationResponse> submitApplication(
            @RequestParam String companyId,
            @RequestParam String fullName,
            @RequestParam String email,
            @RequestParam MultipartFile resume) {

        ApplicationRequest request = new ApplicationRequest();
        request.setCompanyId(companyId);
        request.setFullName(fullName);
        request.setEmail(email);
        request.setResume(resume);

        ApplicationResponse response = applicationService.submitApplication(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/company/{companyId}")
    public ResponseEntity<List<ApplicationResponse>> getApplicationsByCompany(
            @PathVariable String companyId) {
        List<ApplicationResponse> responses = applicationService.getApplicationsByCompany(companyId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}/resume")
    public ResponseEntity<Resource> downloadResume(@PathVariable Long id) {

        Resource file = applicationService.getResumeFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}