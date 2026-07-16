package com.kuldeeplearning.hexagonal.report.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Report {

    private UUID id;
    private String name;
    private ReportStatus status;
    private LocalDateTime createdAt;

    public Report(
            UUID id,
            String name,
            ReportStatus status,
            LocalDateTime createdAt) {

        this.id = id;
        this.name = name;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Business Rule:
     * Submitted reports cannot be validated again.
     */
    public void validate() {

        if (status == ReportStatus.SUBMITTED) {

            throw new IllegalStateException(
                    "Submitted report cannot be validated"
            );
        }

        this.status = ReportStatus.VALIDATED;
    }

    /**
     * Business Rule:
     * Only validated reports can be submitted.
     */
    public void submit() {

        if (status != ReportStatus.VALIDATED) {

            throw new IllegalStateException(
                    "Report must be validated before submission"
            );
        }

        this.status = ReportStatus.SUBMITTED;
    }

    /**
     * Business Rule:
     * Submitted reports cannot be renamed.
     */
    public void rename(String newName) {

        if (isSubmitted()) {

            throw new IllegalStateException(
                    "Submitted report cannot be renamed"
            );
        }

        if (newName == null || newName.isBlank()) {

            throw new IllegalArgumentException(
                    "Report name cannot be blank"
            );
        }

        this.name = newName;
    }

    public boolean isSubmitted() {

        return status == ReportStatus.SUBMITTED;
    }

    public boolean isValidated() {

        return status == ReportStatus.VALIDATED;
    }

    public boolean isDraft() {

        return status == ReportStatus.DRAFT;
    }
}