package com.kuldeeplearning.hexagonal.report.application;

import com.kuldeeplearning.hexagonal.report.domain.Report;

public interface CreateReportUseCase {

    Report createReport(String name);
}
