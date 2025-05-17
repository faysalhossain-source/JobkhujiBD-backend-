package com.faysal.Jobkhujibd_backend.controller;

import com.faysal.Jobkhujibd_backend.model.JobListing;
import com.faysal.Jobkhujibd_backend.repository.JobListingRepository;
import com.faysal.Jobkhujibd_backend.service.JobListingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-listings")

public class JobListingController {


    @Autowired
    private JobListingService jobListingService;

    // Create
    @PostMapping
    public ResponseEntity<JobListing> createJobListing(@RequestBody JobListing jobListing) {
        JobListing createdJob = jobListingService.createJobListing(jobListing);
        return ResponseEntity.ok(createdJob);
    }

    // Read all
    @GetMapping
    public ResponseEntity<List<JobListing>> getAllJobListings() {
        return ResponseEntity.ok(jobListingService.getAllJobListings());
    }

    // Read by ID
    @GetMapping("/{id}")
    public ResponseEntity<JobListing> getJobListingById(@PathVariable Long id) {
        return jobListingService.getJobListingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update
    @PutMapping("/{id}")
    public ResponseEntity<JobListing> updateJobListing(@PathVariable Long id, @RequestBody JobListing updatedJob) {
        return jobListingService.updateJobListing(id, updatedJob)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJobListing(@PathVariable Long id) {
        boolean deleted = jobListingService.deleteJobListing(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
