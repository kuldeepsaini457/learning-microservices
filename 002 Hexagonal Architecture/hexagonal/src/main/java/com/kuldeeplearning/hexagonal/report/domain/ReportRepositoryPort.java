package com.kuldeeplearning.hexagonal.report.domain;

import java.util.Optional;
import java.util.UUID;

public interface ReportRepositoryPort {

    Report save(Report report);

    Optional<Report> findById(UUID id);
}
