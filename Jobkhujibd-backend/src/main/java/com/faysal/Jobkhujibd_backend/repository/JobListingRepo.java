package com.faysal.Jobkhujibd_backend.repository;

import com.faysal.Jobkhujibd_backend.model.JobListing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobListingRepo extends JpaRepository<JobListing, Long> {
}
