package com.kuldeeplearning.hexagonal.validation.adapters.outbound.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ValidationJpaRepository extends JpaRepository<ValidationEntity, UUID> {

    Optional<ValidationEntity> findByReportId(UUID reportId);
}
