package com.faysal.Jobkhujibd_backend.service;

import com.faysal.Jobkhujibd_backend.dto.ApplicationRequest;
import com.faysal.Jobkhujibd_backend.dto.ApplicationResponse;
import com.faysal.Jobkhujibd_backend.handler.ApplicationException;
import com.faysal.Jobkhujibd_backend.model.Application;
import com.faysal.Jobkhujibd_backend.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ApplycationFileStorageService fileStorageService;

    @Transactional
    public ApplicationResponse submitApplication(ApplicationRequest request) {
        // Check if already applied
        if (applicationRepository.existsByEmailAndCompanyId(request.getEmail(), request.getCompanyId())) {
            throw new ApplicationException("You have already applied to this company");
        }

        // Validate file size (5MB max)
        if (request.getResume().getSize() > 5 * 1024 * 1024) {
            throw new ApplicationException("File size exceeds 5MB limit");
        }

        // Store file
        String resumePath = fileStorageService.store(request.getResume());

        // Save application
        Application application = new Application();
        application.setCompanyId(request.getCompanyId());
        application.setFullName(request.getFullName());
        application.setEmail(request.getEmail());
        application.setResumePath(resumePath);

        Application savedApplication = applicationRepository.save(application);

        return mapToResponse(savedApplication);
    }

    public List<ApplicationResponse> getApplicationsByCompany(String companyId) {
        return applicationRepository.findByCompanyId(companyId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public Resource getResumeFile(Long id) {
        Application application = applicationRepository.findById(id)
                .orElseThrow(() -> new ApplicationException("Application not found with id: " + id));

        String filename = application.getResumePath();
        Resource resource = fileStorageService.loadAsResource(filename);

        if (!resource.exists() || !resource.isReadable()) {
            throw new ApplicationException("Could not read file: " + filename);
        }

        return resource;
    }

    private ApplicationResponse mapToResponse(Application application) {
        ApplicationResponse response = new ApplicationResponse();

        response.setId(application.getId());

        response.setCompanyId(application.getCompanyId());

        response.setFullName(application.getFullName());

        response.setEmail(application.getEmail());

        response.setAppliedAt(application.getAppliedAt());

        response.setResumeDownloadUrl("/api/applications/" + application.getId() + "/resume");
        return response;
    }
}