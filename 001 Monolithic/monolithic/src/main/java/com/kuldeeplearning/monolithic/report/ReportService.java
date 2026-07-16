package com.kuldeeplearning.monolithic.report;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final ReportRepository reportRepository;

    public ReportResponse createReport(
            CreateReportRequest request) {

        Report report =
                Report.builder()
                        .id(UUID.randomUUID())
                        .name(request.name())
                        .status(ReportStatus.DRAFT)
                        .createdAt(LocalDateTime.now())
                        .build();

        reportRepository.save(report);

        return map(report);
    }

    public ReportResponse getReport(UUID reportId) {

        Report report =
                reportRepository.findById(reportId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Report not found"));

        return map(report);
    }

    public ReportResponse validateReport(UUID reportId) {

        Report report =
                reportRepository.findById(reportId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Report not found"));

        report.setStatus(
                ReportStatus.VALIDATED
        );

        reportRepository.save(report);

        return map(report);
    }

    public ReportResponse submitReport(UUID reportId) {

        Report report =
                reportRepository.findById(reportId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Report not found"));

        if (report.getStatus() != ReportStatus.VALIDATED) {

            throw new IllegalStateException(
                    "Report must be validated before submission"
            );
        }

        report.setStatus(
                ReportStatus.SUBMITTED
        );

        reportRepository.save(report);

        return map(report);
    }

    private ReportResponse map(
            Report report) {

        return new ReportResponse(
                report.getId(),
                report.getName(),
                report.getStatus(),
                report.getCreatedAt()
        );
    }
}