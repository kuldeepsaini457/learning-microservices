package com.kuldeeplearning.hexagonal.report.adapters.outbound.persistence;

import com.kuldeeplearning.hexagonal.report.domain.*;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReportJpaAdapter
        implements ReportRepositoryPort {

    private final ReportJpaRepository repository;

    @Override
    public Report save(Report report) {

        ReportEntity entity =
                new ReportEntity(
                        report.getId(),
                        report.getName(),
                        report.getStatus(),
                        report.getCreatedAt()
                );

        ReportEntity saved =
                repository.save(entity);

        return map(saved);
    }

    @Override
    public Optional<Report> findById(UUID id) {

        return repository.findById(id)
                .map(this::map);
    }

    private Report map(
            ReportEntity entity) {

        return new Report(
                entity.getId(),
                entity.getName(),
                entity.getStatus(),
                entity.getCreatedAt()
        );
    }
}