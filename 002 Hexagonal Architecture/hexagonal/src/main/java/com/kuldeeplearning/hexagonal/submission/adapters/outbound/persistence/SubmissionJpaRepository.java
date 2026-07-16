package com.kuldeeplearning.hexagonal.submission.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SubmissionJpaRepository extends JpaRepository<SubmissionEntity, UUID> {
}
