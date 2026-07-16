package com.kuldeeplearning.hexagonal.report.adapters.inbound;

import com.kuldeeplearning.hexagonal.report.CreateReportRequest;
import com.kuldeeplearning.hexagonal.report.ReportResponse;
import com.kuldeeplearning.hexagonal.report.application.ReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping
    public ReportResponse createReport(
            @RequestBody
            @Valid
            CreateReportRequest request) {

        return reportService.createReport(
                request
        );
    }

    @GetMapping("/{reportId}")
    public ReportResponse getReport(
            @PathVariable UUID reportId) {

        return reportService.getReport(
                reportId
        );
    }

    @PostMapping("/{reportId}/validate")
    public ReportResponse validateReport(
            @PathVariable UUID reportId) {

        return reportService.validateReport(
                reportId
        );
    }

    @PostMapping("/{reportId}/submit")
    public ReportResponse submitReport(
            @PathVariable UUID reportId) {

        return reportService.submitReport(
                reportId
        );
    }
}