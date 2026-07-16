package com.kuldeeplearning.hexagonal.submission.application;

import com.kuldeeplearning.hexagonal.submission.domain.Submission;

import java.util.UUID;

public interface SubmitSubmissionUseCase {

    Submission submit(UUID reportId);
}
