package com.kuldeeplearning.monolithic.submission;

import com.kuldeeplearning.monolithic.validation.ValidationStatus;
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
