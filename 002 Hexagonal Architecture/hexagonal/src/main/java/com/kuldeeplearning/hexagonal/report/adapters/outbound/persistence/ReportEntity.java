package com.kuldeeplearning.hexagonal.report.adapters.outbound.persistence;

import com.kuldeeplearning.hexagonal.report.domain.ReportStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {

    @Id
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    private LocalDateTime createdAt;
}
