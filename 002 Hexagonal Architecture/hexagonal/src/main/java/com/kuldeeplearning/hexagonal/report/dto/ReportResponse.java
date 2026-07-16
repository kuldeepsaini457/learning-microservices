package com.kuldeeplearning.hexagonal.report.dto;

import com.kuldeeplearning.hexagonal.report.domain.Report;
import com.kuldeeplearning.hexagonal.report.domain.ReportStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReportResponse(
        UUID id,
        String name,
        ReportStatus status,
        LocalDateTime createdAt
) {

    public static ReportResponse from(
            Report report) {

        return new ReportResponse(
                report.getId(),
                report.getName(),
                report.getStatus(),
                report.getCreatedAt()
        );
    }
}