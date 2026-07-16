package com.kuldeeplearning.reportservice.application;

import com.kuldeeplearning.reportservice.domain.model.Report;

public interface CreateReportUseCase {

    Report createReport(String name);
}
