package com.kuldeeplearning.hexagonal.report.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Report {

    @Id
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private ReportStatus status;

    private LocalDateTime createdAt;
}
