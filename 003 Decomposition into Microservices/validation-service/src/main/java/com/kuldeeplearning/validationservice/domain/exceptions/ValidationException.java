package com.kuldeeplearning.validationservice.domain.exceptions;

import java.util.UUID;

public class ValidationException extends RuntimeException{

    public ValidationException(UUID id) {
        super("Report not found: " + id);
    }
}
