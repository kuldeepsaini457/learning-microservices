package com.kuldeeplearning.validationservice.domain.model;

import java.util.UUID;

public class Validation {

    private UUID id;
    private UUID reportId;
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

    public void pass(String remarks) {

        this.status = ValidationStatus.PASSED;
        this.remarks = remarks;
    }

    public void fail(String remarks) {

        this.status = ValidationStatus.FAILED;
        this.remarks = remarks;
    }

    public UUID getId() {
        return id;
    }

    public Validation setId(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getReportId() {
        return reportId;
    }

    public Validation setReportId(UUID reportId) {
        this.reportId = reportId;
        return this;
    }

    public ValidationStatus getStatus() {
        return status;
    }

    public Validation setStatus(ValidationStatus status) {
        this.status = status;
        return this;
    }

    public String getRemarks() {
        return remarks;
    }

    public Validation setRemarks(String remarks) {
        this.remarks = remarks;
        return this;
    }
}