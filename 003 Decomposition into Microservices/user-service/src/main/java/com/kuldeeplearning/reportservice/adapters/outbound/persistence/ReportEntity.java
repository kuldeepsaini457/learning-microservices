package com.kuldeeplearning.reportservice.adapters.outbound.persistence;

import com.kuldeeplearning.reportservice.domain.model.ReportStatus;
import jakarta.persistence.*;
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
