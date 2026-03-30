# Agent Guide

## Architecture

- `io.huangsam.trial.stdlib`: Core utilities & Foundational JDK APIs (Time, Math, Collections).
- `io.huangsam.trial.features.modern`: Stable Java 17/21+ language features (Records, Patterns).
- `io.huangsam.trial.libs`: 3rd party trials (Gson, Guava). Use `libs.gson` for JSON.
- `io.huangsam.trial.concurrent`: Multithreading & Virtual Threads.
- `io.huangsam.trial.model`: Shared POJOs/Records.

## Patterns

- **Explorer Class**: Use `*Explorer.java` for stateless logic (utils/facades).
- **Unit Test**: Use `Test*.java` prefix. Always in `src/test/java`.
- **Coverage**: Aim for 100% JaCoCo coverage (except complex concurrency). Run `jacocoTestReport` to verify density.
- **Stable-First**: No Java preview features. Use Java 17/21 LTS features only.
- **Documentation**: Javadoc is required for all public methods.

## Behavioral Guardrails

- **IF** build fails **THEN** check `config/checkstyle/checkstyle.xml` for `ImportOrder` immediately.
- **IF** adding logging **THEN** reserve for `concurrent` or `libs`; declare as `private static final Logger LOG`.
- **IF** logging data **THEN** prefer `DEBUG` unless it is a lifecycle event.
- **IF** handling imports **THEN** strictly follow the group order in `checkstyle.xml`.
- **IF** creating or renaming a class **THEN** verify if it has fields; use `Explorer` suffix only if stateless (no fields).
