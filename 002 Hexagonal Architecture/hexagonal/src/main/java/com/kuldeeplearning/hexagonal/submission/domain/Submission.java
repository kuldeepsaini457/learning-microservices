package com.kuldeeplearning.hexagonal.submission.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Submission {

    private final UUID id;
    private final UUID reportId;
    private SubmissionStatus status;
    private final LocalDateTime submittedAt;

    public Submission(
            UUID id,
            UUID reportId,
            SubmissionStatus status,
            LocalDateTime submittedAt) {

        this.id = id;
        this.reportId = reportId;
        this.status = status;
        this.submittedAt = submittedAt;
    }

    public UUID getId() {
        return id;
    }

    public UUID getReportId() {
        return reportId;
    }

    public SubmissionStatus getStatus() {
        return status;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void reject() {

        if (status == SubmissionStatus.REJECTED) {

            throw new IllegalStateException(
                    "Submission already rejected"
            );
        }

        status = SubmissionStatus.REJECTED;
    }

    public boolean isRejected() {

        return status == SubmissionStatus.REJECTED;
    }

    public boolean isSubmitted() {

        return status == SubmissionStatus.SUBMITTED;
    }
}
