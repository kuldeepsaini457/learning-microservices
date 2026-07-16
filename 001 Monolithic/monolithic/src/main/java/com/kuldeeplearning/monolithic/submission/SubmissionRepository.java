package com.kuldeeplearning.monolithic.submission;

import com.kuldeeplearning.monolithic.validation.Validation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubmissionRepository
        extends JpaRepository<Submission, UUID> {
}
