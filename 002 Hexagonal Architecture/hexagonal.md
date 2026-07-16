# Phase 02 - Hexagonal Architecture (Ports & Adapters)

## Goal

In Phase 01, we learned how to organize a system as a Modular Monolith.

In this phase, the objective is to understand:

- Hexagonal Architecture
- Ports & Adapters
- Dependency Inversion
- Domain vs Infrastructure
- Rich Domain Models
- Application Services
- Transaction Script vs Domain Model

This phase corresponds to the architectural foundation used by the FTGO application in the book before it is decomposed into microservices.

---

# Relationship with Phase 01

Phase 01:

```text
report/
validation/
submission/
user/
```

focused on:

- Modular Monolith
- Business Capabilities
- Future Bounded Contexts
- Package-by-Feature

Phase 02 keeps the same business modules but changes the internal architecture.

---

# Important Insight

Hexagonal Architecture and Microservices are different concepts.

A system can be:

```text
Monolith
+
Hexagonal Architecture
```

or

```text
Microservices
+
Hexagonal Architecture
```

Hexagonal Architecture is about dependency direction.

Microservices are about deployment boundaries.

---

# What is Hexagonal Architecture?

The core idea:

```text
Dependencies always point inward.
```

The business logic should not depend on:

- Spring
- Hibernate
- PostgreSQL
- REST
- Kafka
- External Systems

Instead, infrastructure depends on business logic.

---

# Traditional Layered Flow

```text
Controller
     ↓
Service
     ↓
Repository
     ↓
Database
```

Business logic becomes tightly coupled to persistence.

Example:

```java
@Service
public class ReportService {

    private final ReportJpaRepository repository;

}
```

The service now depends directly on JPA.

---

# Hexagonal Flow

```text
Controller
     ↓
Use Case
     ↓
Port
     ↓
Adapter
     ↓
Database
```

or

```text
REST
     ↓
Inbound Adapter
     ↓
Use Case
     ↓
Port
     ↓
Outbound Adapter
     ↓
PostgreSQL
```

---

# What is a Port?

A Port is an abstraction.

Usually an interface.

Example:

```java
public interface ReportRepositoryPort {

    Report save(Report report);

    Optional<Report> findById(UUID id);
}
```

The business layer only knows:

```text
I need a way to save reports.
```

It does not know:

```text
PostgreSQL
JPA
Hibernate
MongoDB
```

---

# What is an Adapter?

An Adapter is an implementation of a Port.

Example:

```java
@Component
public class ReportJpaAdapter
        implements ReportRepositoryPort {

}
```

The adapter knows:

- Spring Data JPA
- Hibernate
- PostgreSQL

The domain does not.

---

# Folder Structure

The project uses a Business-First organization.

```text
report

├── domain
├── application
├── adapters
└── dto
```

instead of:

```text
domain
application
adapters
```

at the root level.

Reason:

The learning journey is:

```text
Modular Monolith
    ↓
Microservices
```

and business boundaries are more important than technical layers.

---

# Why Not Use Layered Packaging?

Avoid:

```text
controller/
service/
repository/
entity/
```

because business logic becomes scattered.

Example:

```text
controller/ReportController
service/ReportService
repository/ReportRepository
entity/Report
dto/ReportResponse
```

Everything related to Reports is physically separated.

This makes future microservice extraction difficult.

---

# Why Business-First Packaging?

Preferred:

```text
report
│
├── domain
├── application
├── adapters
└── dto
```

Benefits:

- Better cohesion
- Better alignment with business domains
- Easier navigation
- Easier microservice extraction

---

# Understanding Domain

The Domain represents business concepts.

Example:

```java
Report
ReportStatus
```

The domain should be:

```text
Pure Java
```

The domain must not depend on:

- Spring
- JPA
- PostgreSQL
- REST

---

# Understanding Application Layer

Application Layer contains:

- Use Cases
- Transaction Boundaries
- Orchestration

Examples:

```java
CreateReportUseCase
GetReportUseCase
ValidateReportUseCase
SubmitReportUseCase
```

The Application Layer answers:

```text
What should happen?
```

---

# Understanding Adapters

Adapters connect the application to the outside world.

Examples:

## Inbound Adapters

```text
REST Controllers
CLI
GraphQL
WebSocket
```

Example:

```java
ReportController
```

---

## Outbound Adapters

```text
JPA
MongoDB
Kafka
External APIs
```

Example:

```java
ReportJpaAdapter
```

---

# Dependency Direction

Allowed:

```text
Controller
    ↓
Use Case
    ↓
Port
    ↓
Adapter
```

Not Allowed:

```text
Domain
    ↓
Spring
```

Not Allowed:

```text
Domain
    ↓
JPA
```

Not Allowed:

```text
Domain
    ↓
Database
```

---

# Should Ports Exist?

Question:

```text
Why create ReportRepositoryPort?
JPARepository already exists.
```

Answer:

For simple CRUD systems:

```java
ReportService
    ↓
ReportJpaRepository
```

is often sufficient.

However:

```java
ReportService
    ↓
ReportRepositoryPort
```

removes the dependency on JPA.

Benefits:

- Easier testing
- Infrastructure replacement
- Better separation of concerns

Ports are introduced for architectural flexibility.

---

# Are Ports Part of the Domain?

This is debated.

Two common approaches:

## Approach A

```text
domain
application
ports
adapters
```

## Approach B

```text
domain
    ports
```

For this project:

```text
report
│
├── domain
│   └── ports
```

is acceptable because the domain defines what it needs.

---

# Are Adapters Part of the Domain?

No.

Adapters are infrastructure.

Example:

```text
JPA
Kafka
MongoDB
REST
```

These are implementation details.

Putting adapters inside the domain violates Hexagonal Architecture.

---

# Transaction Script vs Rich Domain Model

One of the most important lessons of Phase 02.

---

## Transaction Script

Business logic lives in services.

Example:

```java
public void submitReport(UUID reportId) {

    Report report = repository.findById(reportId);

    if(report.getStatus() != VALIDATED) {
        throw ...
    }

    report.setStatus(SUBMITTED);
}
```

The service owns business rules.

---

## Rich Domain Model

Business logic lives in the domain.

Example:

```java
report.submit();
```

Domain:

```java
public void submit() {

    if(status != ReportStatus.VALIDATED) {

        throw new IllegalStateException(
                "Report must be validated first"
        );
    }

    status = ReportStatus.SUBMITTED;
}
```

The domain owns business rules.

---

# Why Rich Domain Models Matter

Business rules become centralized.

Without a rich model:

```text
ReportService
SubmissionService
ValidationService
```

may all contain the same rules.

With a rich model:

```java
report.submit();
```

contains the invariant in one place.

---

# Rich Report Domain

The Report entity now contains:

```java
validate()
submit()
rename()
isSubmitted()
isValidated()
isDraft()
```

Business rules belong inside the entity.

---

# Application Service Responsibilities

Application Services should:

```text
Load
→ Invoke Domain
→ Save
```

Example:

```java
Report report = getReport(id);

report.submit();

repository.save(report);
```

The service orchestrates.

The domain decides.

---

# Domain Responsibilities

The domain protects business invariants.

Examples:

```java
report.validate();

report.submit();

report.rename(name);
```

The domain answers:

```text
Is this operation allowed?
```

---

# DTO Mapping

Introduced:

```java
ReportResponse.from(report)
```

Purpose:

Keep mapping logic close to the DTO.

Preferred:

```java
ReportResponse.from(report)
```

Avoid:

```java
ReportMapperUtil.convert(...)
```

which often becomes a dumping ground.

---

# Lombok Discussion

Lombok can be used.

Example:

```java
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
```

The Phase 02 example used explicit constructors and methods for educational clarity.

In production systems Lombok is often acceptable.

---

# Business Capability Terminology

The term:

```text
Business Capability
```

is correct.

Examples:

```text
report
validation
submission
user
```

These can also be viewed as:

```text
Subdomains
```

or later:

```text
Bounded Contexts
```

when moving deeper into Domain-Driven Design.

---

# Architecture Evolution

Phase 01

```text
Modular Monolith
```

↓

Phase 02

```text
Hexagonal Monolith
```

↓

Phase 03

```text
Microservices
```

↓

Phase 04

```text
REST Communication
```

↓

Phase 07

```text
Kafka
```

↓

Phase 08

```text
Saga
```

↓

Phase 09

```text
DDD Aggregates
```

↓

Phase 10

```text
Domain Events
```

↓

Phase 11

```text
Event Sourcing
```

---

# Key Takeaways

1. Hexagonal Architecture is not Microservices.
2. Ports are abstractions.
3. Adapters are implementations.
4. Dependencies point inward.
5. Business logic should not depend on infrastructure.
6. Rich Domain Models are preferred over Transaction Scripts.
7. Application Services orchestrate.
8. Domain Models protect business invariants.
9. Business-first packaging aligns with future microservice extraction.
10. Good microservices often start as a well-designed modular monolith with Hexagonal Architecture.

---

# Interview Questions

### What problem does Hexagonal Architecture solve?

It isolates business logic from infrastructure and external systems.

---

### What is a Port?

An abstraction that defines what the application needs from the outside world.

---

### What is an Adapter?

An implementation of a Port that connects the application to a specific technology.

---

### What is the difference between Transaction Script and Rich Domain Model?

Transaction Script places business rules in services.

Rich Domain Model places business rules inside domain objects.

---

### Why should the domain not depend on Spring?

Because business logic should remain independent from infrastructure concerns.

---

### Why are Ports useful even when using JPA?

They remove direct dependencies on persistence technology and improve flexibility and testability.

---

### Is Hexagonal Architecture required for Microservices?

No.

However, Hexagonal Architecture makes microservice extraction significantly easier.

---

### What is the responsibility of an Application Service?

Orchestration and transaction boundaries.

---

### What is the responsibility of a Domain Model?

Protecting business invariants and expressing business behavior.