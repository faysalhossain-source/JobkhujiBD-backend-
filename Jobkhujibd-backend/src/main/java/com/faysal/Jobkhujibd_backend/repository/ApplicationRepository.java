package com.faysal.Jobkhujibd_backend.repository;


import com.faysal.Jobkhujibd_backend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByCompanyId(String companyId);
    boolean existsByEmailAndCompanyId(String email, String companyId);
}