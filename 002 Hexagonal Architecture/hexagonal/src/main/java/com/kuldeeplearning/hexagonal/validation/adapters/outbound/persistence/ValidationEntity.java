package com.kuldeeplearning.hexagonal.validation.adapters.outbound.persistence;

import com.kuldeeplearning.hexagonal.validation.domain.ValidationStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
