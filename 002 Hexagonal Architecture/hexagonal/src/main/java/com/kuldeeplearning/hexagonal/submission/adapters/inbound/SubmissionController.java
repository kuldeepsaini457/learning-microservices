package com.kuldeeplearning.hexagonal.submission.adapters.inbound;

import com.kuldeeplearning.hexagonal.submission.application.SubmitSubmissionUseCase;
import com.kuldeeplearning.hexagonal.submission.domain.Submission;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/submissions")
@RequiredArgsConstructor
public class SubmissionController {

    private final SubmitSubmissionUseCase submitSubmissionUseCase;

    @PostMapping("/{reportId}")
    public Submission submit(
            @PathVariable UUID reportId) {

        return submitSubmissionUseCase.submit(reportId);
    }
}
