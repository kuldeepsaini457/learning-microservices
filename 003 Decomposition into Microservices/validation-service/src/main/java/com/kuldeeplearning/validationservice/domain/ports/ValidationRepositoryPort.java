package com.kuldeeplearning.validationservice.domain.ports;

import com.kuldeeplearning.validationservice.domain.model.Validation;

import java.util.Optional;
import java.util.UUID;

public interface ValidationRepositoryPort {

    Validation save(Validation validation);

    Optional<Validation> findById(UUID id);

    Optional<Validation> findByReportId(UUID reportId);
}
