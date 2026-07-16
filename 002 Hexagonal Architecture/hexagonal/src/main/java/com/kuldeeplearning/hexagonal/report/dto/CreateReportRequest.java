package com.kuldeeplearning.hexagonal.report.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateReportRequest(

        @NotBlank
        String name
) {
}
