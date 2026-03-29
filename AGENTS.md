# Agent Guide

## Architecture

- `io.huangsam.trial.stdlib`: Foundational JDK APIs (Time, Math, Collections).
- `io.huangsam.trial.features.modern`: Stable Java 17/21+ features (Records, Patterns).
- `io.huangsam.trial.libs`: 3rd party trials (Gson, Guava).
- `io.huangsam.trial.concurrent`: Multithreading & Virtual Threads.
- `io.huangsam.trial.model`: Shared POJOs/Records.

## Patterns

- **Explorer Class**: Use `*Explorer.java` in `src/main/java` for logic.
- **Unit Test**: Use `Test*.java` in `src/test/java` for verification.
- **Coverage**: Aim for 100% JaCoCo coverage except for complex concurrency threads.
- **Stable-First**: No preview features unless explicitly requested.

## Behavioral Guardrails

- **IF** adding Java 21+ syntax **THEN** it belongs in `features.modern`.
- **IF** adding core utilities **THEN** it belongs in `stdlib`.
- **IF** handling JSON/Serialization **THEN** use `libs.gson`.
- **IF** build fails **THEN** check Checkstyle `ImportOrder` immediately.
- **IF** tests pass **THEN** run `jacocoTestReport` to verify density.
