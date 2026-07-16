package com.kuldeeplearning.hexagonal.validation.application;

import com.kuldeeplearning.hexagonal.validation.domain.Validation;

import java.util.UUID;

public interface ValidateReportUseCase {

    Validation validate(UUID reportId);
}
