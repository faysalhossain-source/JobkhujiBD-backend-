package com.faysal.Jobkhujibd_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.isdb.DoctorBackend.constants.Role;

public record UserCreateRequest(
        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotNull(message = "Role cannot be null")
        Role role,

        String address,
        Integer age,
        String gender,
        String phoneNumber
) {
}