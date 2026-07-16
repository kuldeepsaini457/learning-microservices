package com.kuldeeplearning.hexagonal.report;

import com.kuldeeplearning.hexagonal.report.domain.ReportStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReportResponse(

        UUID id,
        String name,
        ReportStatus status,
        LocalDateTime createdAt
) {
}