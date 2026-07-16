package com.kuldeeplearning.monolithic.report;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ReportRepository
        extends JpaRepository<Report, UUID> {
}
