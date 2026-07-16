package com.kuldeeplearning.hexagonal.report.application;

import com.kuldeeplearning.hexagonal.report.domain.Report;

import java.util.UUID;

public interface SubmitReportUseCase {

    Report submitReport(UUID reportId);
}
