package com.kuldeeplearning.monolithic.submission;

import com.kuldeeplearning.monolithic.report.Report;
import com.kuldeeplearning.monolithic.report.ReportRepository;
import com.kuldeeplearning.monolithic.report.ReportStatus;
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
