# Phase 03 - Decomposition into Microservices

## Goal

In Phase 01 we learned:

```text
Modular Monolith
```

In Phase 02 we learned:

```text
Hexagonal Architecture
```

In Phase 03 we learned:

```text
How to identify and extract Microservice boundaries
```

This phase corresponds to the decomposition concepts discussed in Chapter 2 of *Microservices Patterns*.

The primary objective is NOT learning REST, Kafka, Docker, or Kubernetes.

The primary objective is learning:

```text
Service Boundaries
Data Ownership
Business Capability Decomposition
```

---

# Relationship with Previous Phases

## Phase 01

```text
Monolithic Application

report/
validation/
submission/
user/
```

Single Deployment

Single Database

Single JVM

---

## Phase 02

```text
Hexagonal Monolith

report/
    domain/
    application/
    adapters/

validation/
submission/
user/
```

Still:

- Single Deployment
- Single Database
- Single JVM

But better internal architecture.

---

## Phase 03

```text
Microservices

report-service
validation-service
submission-service
user-service
```

Each service becomes independently deployable.

---

# Important Insight

Microservices are NOT created by adding REST APIs.

Microservices are created by:

```text
Defining Proper Service Boundaries
```

REST, Kafka, Service Discovery, and Kubernetes come later.

---

# What is Service Decomposition?

Service decomposition is the process of splitting a monolithic application into independently deployable business services.

The most important question is:

```text
What belongs together?
```

not:

```text
How many services should I create?
```

---

# Decomposition Strategies

According to the book, common decomposition strategies include:

## Business Capability

Example:

```text
Amazon

├── Ordering
├── Payments
├── Shipping
├── Inventory
└── Customer Management
```

Each capability can become a service.

---

## Subdomain

Using Domain Driven Design concepts:

```text
Ordering
Payments
Shipping
Customers
```

Each subdomain may become a microservice.

---

# Business Capabilities in Our Project

The reporting platform contains:

```text
report
validation
submission
user
```

These represent business capabilities.

They are also candidates for future bounded contexts.

---

# Phase 3.1 - Extracting report-service

The first microservice extracted was:

```text
report-service
```

---

## Previous State

```text
hexagonal

├── report
├── validation
├── submission
└── user
```

---

## New State

```text
report-service

└── report

    ├── domain
    ├── application
    ├── adapters
    └── dto
```

The report module was moved into its own Spring Boot application.

---

# report-service Architecture

```text
Controller
      ↓
Use Case
      ↓
Port
      ↓
Adapter
      ↓
PostgreSQL
```

Hexagonal Architecture was preserved.

---

# Database Ownership

A key microservice principle:

```text
A service owns its data.
```

---

## report-service

Database:

```sql
report_db
```

Owns:

```sql
reports
```

only.

---

# Why Separate Databases?

Bad:

```text
report-service
validation-service
      |
      |
      V

shared_database
```

This creates coupling.

---

Good:

```text
report-service
      |
      V
report_db


validation-service
      |
      V
validation_db
```

Each service owns its schema and data.

---

# Phase 3.2 - Identifying Remaining Boundaries

After extracting report-service, the next question was:

```text
What other services should exist?
```

---

# Validation Service

Question:

```text
Can validation exist independently?
```

Answer:

Yes.

Reports can remain in DRAFT forever.

Validation has its own lifecycle.

---

Therefore:

```text
validation-service
```

is a valid service boundary.

---

Owns:

```text
Validation
```

Database:

```sql
validation_db
```

Table:

```sql
validations
```

---

# Submission Service

Question:

```text
Can submission exist independently?
```

Answer:

Yes.

Many reports may never be submitted.

Submission has its own lifecycle.

---

Therefore:

```text
submission-service
```

is a valid service boundary.

---

Owns:

```text
Submission
```

Database:

```sql
submission_db
```

Table:

```sql
submissions
```

---

# User Service

Question:

```text
Can user management be isolated?
```

Answer:

Yes.

User Management is usually a shared business capability.

---

Therefore:

```text
user-service
```

is a valid service boundary.

---

Owns:

```text
User
```

Database:

```sql
user_db
```

Table:

```sql
users
```

---

# Final Target Architecture

```text
                 user-service
                       |
                       |
                       V

report-service ---- validation-service

       |
       |
       V

submission-service
```

Communication is NOT implemented yet.

This phase only defines ownership.

---

# Ownership Matrix

| Service | Owns |
|----------|----------|
| report-service | Reports |
| validation-service | Validations |
| submission-service | Submissions |
| user-service | Users |

---

# Service Data Ownership

A service owns:

```text
Domain
Database
Tables
Business Rules
```

Example:

```text
report-service

Owns:
- Report
- reports table
- Report business logic
```

No other service should directly modify its tables.

---

# Distributed Monolith

One of the most important concepts in this phase.

---

## Example

Bad:

```text
validation-service
```

directly updates:

```sql
report_db.reports
```

owned by:

```text
report-service
```

---

Result:

```text
Distributed Monolith
```

Multiple deployments.

But tightly coupled.

---

# Shared Database Anti-Pattern

Bad:

```text
report-service
validation-service
submission-service
user-service

       |
       |
       V

shared_database
```

---

Why is it bad?

Services become coupled through schema changes.

A single table modification can impact multiple services.

---

Preferred:

```text
report_db
validation_db
submission_db
user_db
```

---

# Why We Did NOT Introduce REST Yet

Many tutorials immediately introduce:

```text
Feign
WebClient
REST
Kafka
```

after creating services.

This hides the real architectural lesson.

Phase 03 focuses on:

```text
Service Boundaries
```

not:

```text
Communication
```

Communication is Phase 04.

---

# Why validateReport() Still Exists

At the end of Phase 03:

```http
POST /reports/{id}/validate
```

may still exist inside:

```text
report-service
```

This is intentional.

The objective is:

```text
Decomposition
```

not:

```text
Inter-Service Communication
```

The endpoint will be revisited in Phase 04.

---

# Service Skeletons

The following services were identified:

```text
report-service
validation-service
submission-service
user-service
```

Each should preserve the Hexagonal Architecture structure:

```text
domain/
application/
adapters/
dto/
```

---

# What NOT To Implement Yet

Do NOT introduce:

```text
REST Communication
Feign Clients
WebClient
Kafka
Docker
Kubernetes
Service Discovery
Circuit Breakers
```

These belong to future phases.

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
Synchronous Communication (REST)
```

↓

Phase 05

```text
Service Discovery
```

↓

Phase 06

```text
Circuit Breakers
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

↓

Phase 12

```text
Docker
```

↓

Phase 13

```text
Kubernetes
```

---

# Key Takeaways

1. Microservices are primarily about service boundaries.
2. REST is not what makes a service a microservice.
3. A service should own its data.
4. Business capabilities are strong candidates for service boundaries.
5. Each service should have its own database.
6. Shared databases create coupling.
7. Distributed Monoliths are one of the biggest microservice anti-patterns.
8. Hexagonal Architecture should be preserved inside each microservice.
9. Service decomposition should happen before introducing communication patterns.
10. Good microservices start from a well-designed modular monolith.

---

# Interview Questions

### What is the most important decision in a microservice architecture?

Choosing the correct service boundaries.

---

### What is decomposition by business capability?

Creating services around business functions or capabilities.

Example:

```text
report-service
validation-service
submission-service
user-service
```

---

### Should each service own its data?

Yes.

Each service should own its database and business rules.

---

### Can microservices share a database?

Technically yes.

Architecturally it creates coupling and is discouraged.

---

### What is a Distributed Monolith?

A system deployed as multiple services but tightly coupled through shared databases or synchronous dependencies.

---

### Why is service decomposition difficult?

Because incorrect boundaries lead to excessive communication, tight coupling, and operational complexity.

---

### What should happen before introducing REST communication?

Service boundaries and data ownership should be clearly defined.

---

# Phase Completion Checklist

Before moving to Phase 04:

```text
✅ report-service created

✅ report-service runs independently

✅ report_db created

✅ Hexagonal Architecture preserved

✅ report module removed from monolith

✅ validation-service identified

✅ submission-service identified

✅ user-service identified

✅ service ownership defined

✅ database ownership defined

✅ no shared database

✅ no shared domain model
```

Once these are complete, Phase 03 is finished and the project is ready for:

```text
Phase 04 - Synchronous Communication (REST)
```

where services will begin communicating with each other for the first time.