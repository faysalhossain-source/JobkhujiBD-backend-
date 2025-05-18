package com.faysal.Jobkhujibd_backend.dto;

import com.faysal.Jobkhujibd_backend.constants.Role;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserResponse {
    private Long id;
    private String email;
    private Role role;

    private String mobile;


    private String city;
    private String state;
    private String pinCode;
    private String companyName;
    private String companyAddress;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String firstName;
    private String lastName;
    private String phoneNumber;
}
