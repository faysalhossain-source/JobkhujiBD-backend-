package com.faysal.Jobkhujibd_backend.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.management.relation.Role;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_JOBSEEKER")
    public class JobseekerUser {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String fullName;

        @Column(unique = true, nullable = false)
        private String email;

        @JsonIgnore
        @Column(nullable = false)
        private String password;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role;

        @Column(nullable = false)
        private String mobile;

        private String experience; // e.g., "3 years", or "Fresher"

        @Column(name = "resume_url")
        private String resumeUrl;

        @Column(name = "created_at")
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        // Constructor (you can use Lombok @AllArgsConstructor, @NoArgsConstructor, etc. instead if preferred)
        public JobseekerUser(String fullName, String email, String password, String mobile, String experience, String resumeUrl) {
            this.fullName = fullName;
            this.email = email;
            this.password = password;
            this.role = role;
            this.mobile = mobile;
            this.experience = experience;
            this.resumeUrl = resumeUrl;
        }


        @PrePersist
        protected void onCreate() {
            createdAt = LocalDateTime.now();
            updatedAt = LocalDateTime.now();
        }

        @PreUpdate
        protected void onUpdate() {
            updatedAt = LocalDateTime.now();
        }

        // Getters and Setters (or use Lombok @Data or @Getter/@Setter)
    }


