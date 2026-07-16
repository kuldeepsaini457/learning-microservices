package com.kuldeeplearning.hexagonal.report.application;

import com.kuldeeplearning.hexagonal.report.domain.Report;
import com.kuldeeplearning.hexagonal.report.domain.ReportRepositoryPort;
import com.kuldeeplearning.hexagonal.report.domain.ReportStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReportService implements
        CreateReportUseCase,
        GetReportUseCase,
        ValidateReportUseCase,
        SubmitReportUseCase {

    private final ReportRepositoryPort repository;

    @Override
    public Report createReport(String name) {

        Report report =
                new Report(
                        UUID.randomUUID(),
                        name,
                        ReportStatus.DRAFT,
                        LocalDateTime.now()
                );

        return repository.save(report);
    }

    @Override
    public Report getReport(UUID reportId) {

        return repository.findById(reportId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Report not found"
                        ));
    }

    @Override
    public Report validateReport(UUID reportId) {

        Report report = getReport(reportId);

        report.validate();

        return repository.save(report);
    }

    @Override
    public Report submitReport(UUID reportId) {

        Report report = getReport(reportId);

        report.submit();

        return repository.save(report);
    }
}