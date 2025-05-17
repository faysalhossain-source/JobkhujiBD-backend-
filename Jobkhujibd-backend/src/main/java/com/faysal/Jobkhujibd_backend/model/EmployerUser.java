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
@Table(name = "T_EMPLOYER")

public class EmployerUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String state;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @Column(name = "company_address", nullable = false)
    private String companyAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructor (or use Lombok @AllArgsConstructor)
    public EmployerUser (String companyName, String email, String password, String mobile,
                String city, String state, String pinCode, String companyAddress, Role role) {
        this.companyName = companyName;
        this.email = email;
        this.password = password;
        this.mobile = mobile;
        this.city = city;
        this.state = state;
        this.pinCode = pinCode;
        this.companyAddress = companyAddress;
        this.role = role;
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
}
