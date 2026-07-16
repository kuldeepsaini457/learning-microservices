package com.kuldeeplearning.monolithic.report;

import java.time.LocalDateTime;
import java.util.UUID;

public record ReportResponse(

        UUID id,
        String name,
        ReportStatus status,
        LocalDateTime createdAt
) {
}