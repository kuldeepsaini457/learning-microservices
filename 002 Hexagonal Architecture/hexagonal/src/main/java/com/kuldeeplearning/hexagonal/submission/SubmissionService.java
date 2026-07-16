package com.kuldeeplearning.hexagonal.submission;

import com.kuldeeplearning.hexagonal.report.domain.Report;
import com.kuldeeplearning.hexagonal.report.ReportRepository;
import com.kuldeeplearning.hexagonal.report.domain.ReportStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService {

    private final SubmissionRepository repository;
    private final ReportRepository reportRepository;

    public Submission submit(
            UUID reportId) {

        Report report =
                reportRepository.findById(reportId)
                        .orElseThrow();

        if (report.getStatus()
                != ReportStatus.VALIDATED) {

            throw new IllegalStateException(
                    "Report must be validated"
            );
        }

        report.setStatus(
                ReportStatus.SUBMITTED
        );

        reportRepository.save(report);

        Submission submission =
                Submission.builder()
                        .id(UUID.randomUUID())
                        .reportId(reportId)
                        .status(
                                SubmissionStatus.SUBMITTED
                        )
                        .submittedAt(
                                LocalDateTime.now()
                        )
                        .build();

        return repository.save(submission);
    }
}
