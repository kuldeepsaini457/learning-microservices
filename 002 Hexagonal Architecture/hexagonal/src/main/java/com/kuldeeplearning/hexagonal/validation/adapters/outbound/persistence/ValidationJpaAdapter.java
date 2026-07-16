package com.kuldeeplearning.hexagonal.validation.adapters.outbound.persistence;

import com.kuldeeplearning.hexagonal.validation.domain.Validation;
import com.kuldeeplearning.hexagonal.validation.domain.ValidationRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ValidationJpaAdapter implements ValidationRepositoryPort {

    private final ValidationJpaRepository repository;

    @Override
    public Validation save(Validation validation) {

        ValidationEntity entity = new ValidationEntity(
                validation.getId(),
                validation.getReportId(),
                validation.getStatus(),
                validation.getRemarks()
        );

        ValidationEntity saved = repository.save(entity);

        return map(saved);
    }

    @Override
    public Optional<Validation> findById(UUID id) {

        return repository.findById(id)
                .map(this::map);
    }

    @Override
    public Optional<Validation> findByReportId(UUID reportId) {

        return repository.findByReportId(reportId)
                .map(this::map);
    }

    private Validation map(ValidationEntity entity) {

        return new Validation(
                entity.getId(),
                entity.getReportId(),
                entity.getStatus(),
                entity.getRemarks()
        );
    }
}
