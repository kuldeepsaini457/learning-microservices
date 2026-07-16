package com.kuldeeplearning.monolithic.validation;

import com.kuldeeplearning.monolithic.report.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValidationService {

    private final ValidationRepository repository;
    private final ReportRepository reportRepository;

    public Validation validateReport(
            UUID reportId) {

        Report report =
                reportRepository.findById(reportId)
                        .orElseThrow();

        report.setStatus(
                ReportStatus.VALIDATED
        );

        reportRepository.save(report);

        Validation validation =
                Validation.builder()
                        .id(UUID.randomUUID())
                        .reportId(reportId)
                        .status(ValidationStatus.PASSED)
                        .remarks("Validation Successful")
                        .build();

        return repository.save(validation);
    }
}