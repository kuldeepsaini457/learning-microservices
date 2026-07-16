package com.kuldeeplearning.hexagonal.validation.application;

import com.kuldeeplearning.hexagonal.report.domain.Report;
import com.kuldeeplearning.hexagonal.report.domain.ReportRepositoryPort;
import com.kuldeeplearning.hexagonal.validation.domain.Validation;
import com.kuldeeplearning.hexagonal.validation.domain.ValidationRepositoryPort;
import com.kuldeeplearning.hexagonal.validation.domain.ValidationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidationService implements ValidateReportUseCase {

    private final ValidationRepositoryPort repository;
    private final ReportRepositoryPort reportRepository;

    @Override
    public Validation validate(UUID reportId) {

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        report.validate();

        reportRepository.save(report);

        Validation validation = new Validation(
                UUID.randomUUID(),
                reportId,
                ValidationStatus.PASSED,
                "Validation successful"
        );

        return repository.save(validation);
    }
}
