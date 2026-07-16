package com.kuldeeplearning.hexagonal.report;

import com.kuldeeplearning.hexagonal.report.domain.ReportRepositoryPort;

/**
 * Temporary compatibility boundary for modules not yet refactored in phase 002.
 * New report code must depend on {@link ReportRepositoryPort} instead.
 */
public interface ReportRepository extends ReportRepositoryPort {
}
