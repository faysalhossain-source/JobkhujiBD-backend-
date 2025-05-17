package com.faysal.Jobkhujibd_backend.dto;

import jakarta.validation.constraints.Email;
import com.faysal.Jobkhujibd_backend.constants.Role;

public record UserUpdateRequest(
        @Email(message = "Email should be valid")
        String email,

        Role role,
        String mobile,
        
        

        String city,
        String state,
        String pinCode,

        String companyName,
        String companyAddress
) {
}
