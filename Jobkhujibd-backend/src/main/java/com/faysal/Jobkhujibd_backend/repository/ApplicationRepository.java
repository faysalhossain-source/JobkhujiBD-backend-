package com.faysal.Jobkhujibd_backend.repository;

import com.faysal.Jobkhujibd_backend.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {


    boolean existsByEmailAndCompanyId(String email, String companyId);
    List<Application> findByCompanyId(String companyId);


}