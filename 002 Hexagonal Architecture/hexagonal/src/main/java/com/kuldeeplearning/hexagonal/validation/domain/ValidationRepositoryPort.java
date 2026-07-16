package com.kuldeeplearning.hexagonal.validation.domain;

import java.util.Optional;
import java.util.UUID;

public interface ValidationRepositoryPort {

    Validation save(Validation validation);

    Optional<Validation> findById(UUID id);

    Optional<Validation> findByReportId(UUID reportId);
}
