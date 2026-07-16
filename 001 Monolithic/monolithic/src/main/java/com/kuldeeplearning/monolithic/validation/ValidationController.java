package com.kuldeeplearning.monolithic.validation;

import com.kuldeeplearning.monolithic.report.CreateReportRequest;
import com.kuldeeplearning.monolithic.report.ReportResponse;
import com.kuldeeplearning.monolithic.report.ReportService;
import jakarta.validation.Valid;
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