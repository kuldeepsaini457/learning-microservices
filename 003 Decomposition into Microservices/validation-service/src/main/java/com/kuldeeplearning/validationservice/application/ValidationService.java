package com.kuldeeplearning.validationservice.application;

import com.kuldeeplearning.validationservice.domain.model.Validation;
import com.kuldeeplearning.validationservice.domain.ports.ValidationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidationService implements CreateValidationUseCase {

    private final ValidationRepositoryPort repository;

    @Override
    public Validation validate(String name) {
        // Will implement in future
        return null;
    }

}