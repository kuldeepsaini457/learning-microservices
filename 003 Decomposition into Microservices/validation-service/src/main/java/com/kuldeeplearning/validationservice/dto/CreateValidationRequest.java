package com.kuldeeplearning.validationservice.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateValidationRequest(

        @NotBlank
        String name
) {
}
