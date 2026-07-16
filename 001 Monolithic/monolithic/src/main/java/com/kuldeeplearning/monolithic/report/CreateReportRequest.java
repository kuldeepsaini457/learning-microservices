package com.kuldeeplearning.monolithic.report;

import jakarta.validation.constraints.NotBlank;

public record CreateReportRequest(

        @NotBlank
        String name
) {
}
