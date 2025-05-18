package com.faysal.Jobkhujibd_backend.dto;

import com.faysal.Jobkhujibd_backend.constants.Role;
import jakarta.validation.constraints.Email;

public record UserUpdateRequest(
        @Email(message = "Email should be valid")
        String email,

        Role role,
        String mobile,

        String city,
        String state,
        String pinCode,

        String companyName,
        String companyAddress,
        String firstName,
        String lastName,
        String phoneNumber
) {
}
