package com.kuldeeplearning.hexagonal.report;

import com.kuldeeplearning.hexagonal.report.domain.Report;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository
        extends JpaRepository<Report, UUID> {
}
