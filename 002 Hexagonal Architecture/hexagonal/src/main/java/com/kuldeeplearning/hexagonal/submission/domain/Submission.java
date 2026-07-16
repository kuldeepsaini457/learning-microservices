package com.kuldeeplearning.hexagonal.submission;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "submissions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {

    @Id
    private UUID id;

    private UUID reportId;

    @Enumerated(EnumType.STRING)
    private SubmissionStatus status;

    private LocalDateTime submittedAt;
}
