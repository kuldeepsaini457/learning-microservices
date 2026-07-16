package com.kuldeeplearning.reportservice.application;

import com.kuldeeplearning.reportservice.domain.model.Report;

import java.util.UUID;

public interface GetReportUseCase {

    Report getReport(UUID reportId);
}
