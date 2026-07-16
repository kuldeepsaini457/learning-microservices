package com.kuldeeplearning.hexagonal.report.application;

import com.kuldeeplearning.hexagonal.report.domain.Report;
import com.kuldeeplearning.hexagonal.report.domain.ReportRepositoryPort;
import com.kuldeeplearning.hexagonal.report.domain.ReportStatus;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ReportServiceTests {

    private final InMemoryReportRepository repository = new InMemoryReportRepository();
    private final ReportService service = new ReportService(repository);

    @Test
    void createsDraftReport() {

        Report report = service.createReport("Quarterly report");

        assertThat(report.getStatus()).isEqualTo(ReportStatus.DRAFT);
        assertThat(repository.findById(report.getId())).contains(report);
    }

    @Test
    void validatesReport() {

        Report report = service.createReport("Quarterly report");

        Report validated = service.validateReport(report.getId());

        assertThat(validated.getStatus()).isEqualTo(ReportStatus.VALIDATED);
    }

    @Test
    void submitsValidatedReport() {

        Report report = service.createReport("Quarterly report");
        service.validateReport(report.getId());

        Report submitted = service.submitReport(report.getId());

        assertThat(submitted.getStatus()).isEqualTo(ReportStatus.SUBMITTED);
    }

    @Test
    void rejectsSubmissionOfDraftReport() {

        Report report = service.createReport("Quarterly report");

        assertThatThrownBy(() -> service.submitReport(report.getId()))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Report must be validated before submission");
    }

    private static class InMemoryReportRepository implements ReportRepositoryPort {

        private final Map<UUID, Report> reports = new HashMap<>();

        @Override
        public Report save(Report report) {

            reports.put(report.getId(), report);
            return report;
        }

        @Override
        public Optional<Report> findById(UUID id) {

            return Optional.ofNullable(reports.get(id));
        }
    }
}
