# Phase 01 - Monolithic Architecture

## Goal

Understand:

- What a monolith is
- Why monoliths became popular
- Why monoliths eventually become difficult to maintain
- What a Modular Monolith is
- Why a Modular Monolith is a better starting point than a traditional layered architecture

This phase corresponds to:

- Chapter 1 - Escaping Monolithic Hell
- Chapter 2 (beginning) - Decomposition Strategies

---

# What is a Monolith?

A monolith is an application that is:

- Developed as a single codebase
- Built as a single deployable artifact
- Runs as a single process/JVM
- Usually uses a single database
- Deployed as a single unit

Example:

```
+--------------------------+
| Regulatory Reporting App |
+--------------------------+
            |
            |
            V
      PostgreSQL
```

Characteristics:

- One Git repository
- One CI/CD pipeline
- One deployment
- One JVM
- One database

---

# What Makes Something a Monolith?

Many developers incorrectly think package structure determines whether something is a monolith.

Wrong.

These are BOTH monoliths:

## Layered Monolith

```text
controller/
service/
repository/
entity/
```

## Modular Monolith

```text
report/
validation/
submission/
user/
```

Both are still:

- One application
- One deployment
- One database
- One runtime

The difference is only code organization.

---

# Why Monoliths Were Popular

Monoliths solved many problems:

- Easy to develop
- Easy to test
- Easy to deploy
- Easy to debug
- Easy to run locally
- Easy to make large changes

For small teams and small products, monoliths are often the best choice.

---

# Why Monoliths Become Difficult

As the business grows:

- More features
- More developers
- More modules
- More dependencies

Problems begin appearing:

## Complexity

No developer understands the entire system.

## Slow Development

Builds become slower.

Startup time increases.

IDE performance degrades.

## Difficult Deployments

A small change requires redeploying the entire application.

## Scaling Issues

Different modules have different resource requirements.

Example:

- Validation Engine → CPU intensive
- Reporting → Database intensive

Yet both run inside the same application.

## Technology Lock-In

Changing frameworks becomes difficult.

---

# Traditional Layered Architecture

Historically many Spring Boot applications were organized like this:

```text
controller/
service/
repository/
entity/
config/
exception/
```

Example:

```text
controller/
    ReportController
    ValidationController
    UserController

service/
    ReportService
    ValidationService
    UserService

repository/
    ReportRepository
    ValidationRepository
    UserRepository
```

---

# Why Developers Chose Layered Architecture

Layered architecture was not a mistake.

Historically it made sense.

Reasons:

## 1. Textbooks Taught Layers

Traditional enterprise architecture taught:

```text
Presentation Layer
Business Layer
Persistence Layer
```

This became:

```text
controller/
service/
repository/
```

---

## 2. Frameworks Encouraged It

Spring examples historically looked like:

```java
@Controller
@Service
@Repository
```

Developers naturally organized folders the same way.

---

## 3. Small Applications Didn't Suffer

If the application contained:

- 5 controllers
- 5 services
- 5 repositories

the structure was easy to understand.

Problems only appeared later.

---

# Problem with Layered Structure

Suppose we need everything related to Reports.

We must search:

```text
controller/ReportController
service/ReportService
repository/ReportRepository
entity/Report
dto/ReportRequest
dto/ReportResponse
mapper/ReportMapper
```

Business logic is scattered.

This is called:

## Low Cohesion

Things that belong together are physically separated.

---

# Modular Monolith

Instead of organizing by technical layers:

```text
controller/
service/
repository/
```

we organize by business capability.

Example:

```text
report/
validation/
submission/
user/
```

Each module contains:

```text
report/

├── ReportController
├── ReportService
├── ReportRepository
├── Report
├── DTOs
└── Exceptions
```

Now everything related to Reports is in one place.

---

# Benefits of a Modular Monolith

## Better Cohesion

All report-related code is together.

## Easier Navigation

Developers find functionality faster.

## Better Alignment with Business

Folders match business capabilities.

## Easier Refactoring

Future microservices are easier to extract.

---

# Modular Monolith != Microservices

This is still:

```text
One Deployment
One Database
One JVM
```

Therefore:

```text
Modular Monolith
```

NOT

```text
Microservices
```

---

# Relationship to Domain Driven Design

Our modules already resemble future bounded contexts.

```text
report/
validation/
submission/
user/
```

Each module owns its business logic.

Later these modules can become independent services.

---

# Future Evolution

Phase 01

```text
Monolith

report/
validation/
submission/
user/
```

↓

Phase 02

```text
Hexagonal Architecture

report/
validation/
submission/
user/

    domain
    application
    adapters
```

↓

Phase 03

```text
report-service
validation-service
submission-service
user-service
```

↓

Phase 04+

REST

↓

Kafka

↓

Saga

↓

DDD Aggregates

↓

Domain Events

↓

Event Sourcing

↓

Docker

↓

Kubernetes
```

---

# Key Takeaways

1. Monolith is defined by deployment boundaries, not package structure.
2. Layered architecture was historically a reasonable choice.
3. Layered architecture becomes difficult as systems grow.
4. Modular Monolith organizes code around business capabilities.
5. Modular Monolith is still a monolith.
6. Modular Monolith is the best starting point for learning microservices.
7. Good microservices usually start as a well-designed modular monolith.
8. Our modules:

```text
report/
validation/
submission/
user/
```

are future bounded contexts and future microservices.

---

# Interview Questions

### What is a monolith?

A single deployable application that runs as one process and is typically backed by a single database.

### What is a modular monolith?

A monolith whose internal structure is organized around business capabilities or modules instead of technical layers.

### Is package structure enough to make something a microservice?

No.

Microservices require independent deployment and runtime boundaries.

### Why is a modular monolith preferable to a layered monolith?

Because it improves cohesion, aligns with business domains, and makes future decomposition easier.

### When should a team start with microservices?

Usually not at the beginning.

Start with a modular monolith and evolve toward microservices when organizational and scaling requirements justify the complexity.