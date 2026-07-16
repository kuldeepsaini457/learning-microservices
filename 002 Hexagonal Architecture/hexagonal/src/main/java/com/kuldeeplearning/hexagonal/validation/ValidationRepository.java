package com.kuldeeplearning.hexagonal.validation;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ValidationRepository
        extends JpaRepository<Validation, UUID> {

    Optional<Validation> findByReportId(
            UUID reportId
    );
}
