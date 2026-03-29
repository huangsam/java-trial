# Agent Guide

## Architecture

- `io.huangsam.trial.stdlib`: Foundational JDK APIs (Time, Math, Collections).
- `io.huangsam.trial.features.modern`: Stable Java 17/21+ features (Records, Patterns).
- `io.huangsam.trial.libs`: 3rd party trials (Gson, Guava).
- `io.huangsam.trial.concurrent`: Multithreading & Virtual Threads.
- `io.huangsam.trial.model`: Shared POJOs/Records.

## Patterns

- **Explorer Class**: Use `*Explorer.java` for stateless logic (utils/facades). If the class has instance-level state (e.g., initialized fields), do NOT use the suffix. Always place in `src/main/java`.
- **Unit Test**: Use `Test*.java` prefix for all unit tests. Always place in `src/test/java`.
- **Coverage**: Aim for 100% JaCoCo coverage except for complex concurrency threads.
- **Stable-First**: No preview features unless explicitly requested.

## Behavioral Guardrails

- **IF** adding Java 21+ syntax **THEN** it belongs in `features.modern`.
- **IF** adding core utilities **THEN** it belongs in `stdlib`.
- **IF** handling JSON/Serialization **THEN** use `libs.gson`.
- **IF** build fails **THEN** check Checkstyle `ImportOrder` immediately.
- **IF** tests pass **THEN** run `jacocoTestReport` to verify density.
- **IF** adding classes with public methods **THEN** add Javadoc for each method.
- **IF** adding logging **THEN** it must be reserved for `concurrent` logic or `libs` integrations.
- **IF** adding logging **THEN** declare the logger as `private static final Logger LOG`.
- **IF** logging data **THEN** prefer `DEBUG` unless it's a lifecycle event.
- **IF** handling imports, please look at `checkstyle.xml` for the import order.
