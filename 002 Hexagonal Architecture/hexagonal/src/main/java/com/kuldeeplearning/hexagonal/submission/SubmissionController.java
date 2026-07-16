package com.kuldeeplearning.hexagonal.submission;

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

    private final SubmissionService service;

    @PostMapping("/{reportId}")
    public Submission submit(
            @PathVariable UUID reportId) {

        return service.submit(reportId);
    }
}
