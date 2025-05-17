package com.faysal.Jobkhujibd_backend.dto;

public record FieldError(
        String field,
        String errorCode,
        String errorMessage
) {
}