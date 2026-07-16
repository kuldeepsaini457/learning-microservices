package com.kuldeeplearning.validationservice.adapters.inbound;

import com.kuldeeplearning.validationservice.application.*;
import com.kuldeeplearning.validationservice.dto.*;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/validations")
@RequiredArgsConstructor
public class ValidationController {

    private final CreateValidationUseCase createValidationUseCase;

    @PostMapping("/{id}")
    public ValidationResponse getValidation(
            @PathVariable UUID id) {

        return null;
    }

    @GetMapping("/")
    public ValidationResponse validateReport() {

        return ValidationResponse.from(createValidationUseCase.validate("id")
        );
    }

}