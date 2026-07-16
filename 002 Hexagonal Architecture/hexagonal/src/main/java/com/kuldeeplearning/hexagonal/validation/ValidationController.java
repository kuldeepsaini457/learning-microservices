package com.kuldeeplearning.hexagonal.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/validations")
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping("/{reportId}")
    public Validation validate(
            @PathVariable UUID reportId) {

        return validationService.validateReport(
                reportId
        );
    }
}