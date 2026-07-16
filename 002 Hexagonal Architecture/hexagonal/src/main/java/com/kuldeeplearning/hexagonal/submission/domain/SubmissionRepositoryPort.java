package com.kuldeeplearning.hexagonal.submission.domain;

import java.util.Optional;
import java.util.UUID;

public interface SubmissionRepositoryPort {

    Submission save(Submission submission);

    Optional<Submission> findById(UUID id);
}
