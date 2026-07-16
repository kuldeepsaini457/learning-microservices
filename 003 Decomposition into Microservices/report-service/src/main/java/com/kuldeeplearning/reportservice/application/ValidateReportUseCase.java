package com.kuldeeplearning.reportservice.application;

import com.kuldeeplearning.reportservice.domain.model.Report;

import java.util.UUID;

public interface ValidateReportUseCase {

    Report validateReport(UUID reportId);
}
