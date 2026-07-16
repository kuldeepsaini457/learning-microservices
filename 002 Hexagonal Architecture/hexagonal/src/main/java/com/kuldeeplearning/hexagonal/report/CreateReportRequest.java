package com.kuldeeplearning.hexagonal.report;

import jakarta.validation.constraints.NotBlank;

public record CreateReportRequest(

        @NotBlank
        String name
) {
}
