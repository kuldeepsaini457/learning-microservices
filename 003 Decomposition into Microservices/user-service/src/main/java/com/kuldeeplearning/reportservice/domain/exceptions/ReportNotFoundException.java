package com.kuldeeplearning.reportservice.domain.exceptions;

import java.util.UUID;

public class ReportNotFoundException extends RuntimeException{

    public ReportNotFoundException(UUID id) {
        super("Report not found: " + id);
    }
}
