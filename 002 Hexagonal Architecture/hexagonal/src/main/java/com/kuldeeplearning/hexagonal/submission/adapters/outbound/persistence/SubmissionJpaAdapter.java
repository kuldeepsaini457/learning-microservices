package com.kuldeeplearning.hexagonal.submission.adapters.outbound.persistence;

import com.kuldeeplearning.hexagonal.submission.domain.Submission;
import com.kuldeeplearning.hexagonal.submission.domain.SubmissionRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SubmissionJpaAdapter implements SubmissionRepositoryPort {

    private final SubmissionJpaRepository repository;

    @Override
    public Submission save(Submission submission) {

        SubmissionEntity entity = new SubmissionEntity(
                submission.getId(),
                submission.getReportId(),
                submission.getStatus(),
                submission.getSubmittedAt()
        );

        SubmissionEntity saved = repository.save(entity);

        return map(saved);
    }

    @Override
    public Optional<Submission> findById(UUID id) {

        return repository.findById(id)
                .map(this::map);
    }

    private Submission map(SubmissionEntity entity) {

        return new Submission(
                entity.getId(),
                entity.getReportId(),
                entity.getStatus(),
                entity.getSubmittedAt()
        );
    }
}
