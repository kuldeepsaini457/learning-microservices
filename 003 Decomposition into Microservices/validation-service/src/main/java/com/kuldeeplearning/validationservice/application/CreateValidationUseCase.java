package com.kuldeeplearning.validationservice.application;

import com.kuldeeplearning.validationservice.domain.model.Validation;

import java.util.UUID;

public interface CreateValidationUseCase {

    Validation validate(String name);

}
