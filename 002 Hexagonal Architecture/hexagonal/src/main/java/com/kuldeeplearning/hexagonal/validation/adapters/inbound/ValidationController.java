package com.kuldeeplearning.hexagonal.validation.adapters.inbound;

import com.kuldeeplearning.hexagonal.validation.application.ValidateReportUseCase;
import com.kuldeeplearning.hexagonal.validation.domain.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/validations")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidateReportUseCase validateReportUseCase;

    @PostMapping("/{reportId}")
    public Validation validate(
            @PathVariable UUID reportId) {

        return validateReportUseCase.validate(reportId);
    }
}