package com.kuldeeplearning.hexagonal.submission.application;

import com.kuldeeplearning.hexagonal.report.domain.Report;
import com.kuldeeplearning.hexagonal.report.domain.ReportRepositoryPort;
import com.kuldeeplearning.hexagonal.submission.domain.Submission;
import com.kuldeeplearning.hexagonal.submission.domain.SubmissionRepositoryPort;
import com.kuldeeplearning.hexagonal.submission.domain.SubmissionStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SubmissionService implements SubmitSubmissionUseCase {

    private final SubmissionRepositoryPort repository;
    private final ReportRepositoryPort reportRepository;

    @Override
    public Submission submit(UUID reportId) {

        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Report not found"));

        report.submit();

        reportRepository.save(report);

        Submission submission = new Submission(
                UUID.randomUUID(),
                reportId,
                SubmissionStatus.SUBMITTED,
                LocalDateTime.now()
        );

        return repository.save(submission);
    }
}
