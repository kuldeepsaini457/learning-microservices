package com.kuldeeplearning.hexagonal.report.adapters.inbound;

import com.kuldeeplearning.hexagonal.report.application.*;
import com.kuldeeplearning.hexagonal.report.dto.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final CreateReportUseCase createReportUseCase;
    private final GetReportUseCase getReportUseCase;
    private final ValidateReportUseCase validateReportUseCase;
    private final SubmitReportUseCase submitReportUseCase;

    @PostMapping
    public ReportResponse createReport(
            @Valid
            @RequestBody
            CreateReportRequest request) {

        return ReportResponse.from(
                createReportUseCase.createReport(
                        request.name()
                )
        );
    }

    @GetMapping("/{id}")
    public ReportResponse getReport(
            @PathVariable UUID id) {

        return ReportResponse.from(
                getReportUseCase.getReport(id)
        );
    }

    @PostMapping("/{id}/validate")
    public ReportResponse validateReport(
            @PathVariable UUID id) {

        return ReportResponse.from(
                validateReportUseCase.validateReport(id)
        );
    }

    @PostMapping("/{id}/submit")
    public ReportResponse submitReport(
            @PathVariable UUID id) {

        return ReportResponse.from(
                submitReportUseCase.submitReport(id)
        );
    }
}