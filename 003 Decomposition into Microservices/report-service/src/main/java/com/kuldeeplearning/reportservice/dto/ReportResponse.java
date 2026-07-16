package com.kuldeeplearning.reportservice.dto;

import com.kuldeeplearning.reportservice.domain.model.Report;
import com.kuldeeplearning.reportservice.domain.model.ReportStatus;

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