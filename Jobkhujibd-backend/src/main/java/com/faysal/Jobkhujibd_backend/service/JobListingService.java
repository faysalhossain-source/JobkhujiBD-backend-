package com.faysal.Jobkhujibd_backend.service;

import com.faysal.Jobkhujibd_backend.model.JobListing;

import com.faysal.Jobkhujibd_backend.repository.JobListingRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JobListingService {


    private final JobListingRepo jobListingRepository ;

    // Create
    public JobListing createJobListing(JobListing jobListing) {
        return jobListingRepository.save(jobListing);
    }

    // Read all
    public List<JobListing> getAllJobListings() {
        return jobListingRepository.findAll();
    }

    // Read by ID
    public Optional<JobListing> getJobListingById(Long id) {
        return jobListingRepository.findById(id);
    }

    // Update
    public Optional<JobListing> updateJobListing(Long id, JobListing updatedJob) {
        return jobListingRepository.findById(id).map(job -> {
            job.setJobTitle(updatedJob.getJobTitle());
            job.setCategory(updatedJob.getCategory());
            job.setLocation(updatedJob.getLocation());
            job.setExperience(updatedJob.getExperience());
            job.setPay_scale(updatedJob.getPay_scale());
            job.setDescription(updatedJob.getDescription());
            job.setCreateDate(updatedJob.getCreateDate());
            return jobListingRepository.save(job);
        });
    }

    // Delete
    public boolean deleteJobListing(Long id) {
        return jobListingRepository.findById(id).map(job -> {
            jobListingRepository.delete(job);
            return true;
        }).orElse(false);
    }
}
