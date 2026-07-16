package com.kuldeeplearning.hexagonal.submission.adapters.outbound.persistence;

import com.kuldeeplearning.hexagonal.submission.domain.SubmissionStatus;
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
@Table(name = "submissions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionEntity {

    @Id
    private UUID id;

    private UUID reportId;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private LocalDateTime submittedAt;
}
