package com.kuldeeplearning.hexagonal.validation.domain;

import java.util.UUID;

public class Validation {

    private final UUID id;
    private final UUID reportId;
    private ValidationStatus status;
    private String remarks;

    public Validation(
            UUID id,
            UUID reportId,
            ValidationStatus status,
            String remarks) {

        this.id = id;
        this.reportId = reportId;
        this.status = status;
        this.remarks = remarks;
    }

    public UUID getId() {
        return id;
    }

    public UUID getReportId() {
        return reportId;
    }

    public ValidationStatus getStatus() {
        return status;
    }

    public String getRemarks() {
        return remarks;
    }

    public void pass(String remarks) {

        this.status = ValidationStatus.PASSED;
        this.remarks = remarks;
    }

    public void fail(String remarks) {

        this.status = ValidationStatus.FAILED;
        this.remarks = remarks;
    }

    public boolean isPassed() {

        return status == ValidationStatus.PASSED;
    }

    public boolean isFailed() {

        return status == ValidationStatus.FAILED;
    }
}