package com.kuldeeplearning.reportservice.domain.ports;

import com.kuldeeplearning.reportservice.domain.model.Report;

import java.util.Optional;
import java.util.UUID;

public interface ReportRepositoryPort {

    Report save(Report report);

    Optional<Report> findById(UUID id);
}
