package com.kuldeeplearning.validationservice.dto;

import com.kuldeeplearning.validationservice.domain.model.Validation;
import com.kuldeeplearning.validationservice.domain.model.ValidationStatus;

import java.util.UUID;

public record ValidationResponse(
        UUID id,
        UUID reportId,
        ValidationStatus status,
        String remarks
) {

    public static ValidationResponse from(
            Validation validation) {

        return new ValidationResponse(
                validation.getId(),
                validation.getReportId(),
                validation.getStatus(),
                validation.getRemarks()
        );
    }
}