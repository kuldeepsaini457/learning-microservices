# Phase 002: Hexagonal Architecture

## Goal

Refactor the existing monolith toward Ports and Adapters without changing the reporting business behavior, HTTP endpoints, or PostgreSQL `reports` table. This phase is still a modular monolith, not a microservices implementation.

## Scope

Only the `report` capability is hexagonalized in this milestone. `validation`, `submission`, and `user` remain in their current structure so the architectural change can be learned in one bounded slice before it is repeated elsewhere.

## Architecture

```text
REST controller -> report use case -> ReportRepositoryPort
                                         |
                                  ReportJpaAdapter
                                         |
                                 Spring Data JPA -> PostgreSQL
```

`report/domain` contains plain Java business types and the repository port. It has no Spring, JPA, or PostgreSQL dependency. `report/application` contains the four use cases: create, get, validate, and submit. `report/adapters` holds the inbound REST controller and outbound JPA persistence implementation. DTOs are in `report/dto`.

## Tradeoff And Lesson

The old `ReportRepository` name remains as a temporary compatibility interface because the untouched validation and submission modules still use it. New report code must depend on `ReportRepositoryPort`; the JPA adapter implements both interfaces. This preserves current behavior while making the new dependency direction visible.

The next learning step is Phase 2.2: compare a transaction script with a richer domain model, test ports directly, and examine why this boundary simplifies later microservice extraction.
