package com.kuldeeplearning.validationservice.adapters.outbound.persistence;

import com.kuldeeplearning.validationservice.domain.model.ValidationStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "validations")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidationEntity {

    @Id
    private UUID id;

    private UUID reportId;

    @Enumerated(EnumType.STRING)
    private ValidationStatus status;

    private String remarks;
}