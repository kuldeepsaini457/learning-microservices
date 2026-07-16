package com.kuldeeplearning.monolithic.validation;

import com.kuldeeplearning.monolithic.report.ReportStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "validations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Validation {

    @Id
    private UUID id;

    private UUID reportId;

    @Enumerated(EnumType.STRING)
    private ValidationStatus status;

    private String remarks;
}