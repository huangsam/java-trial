# Java Trial

[![GitHub Actions Workflow Status](https://img.shields.io/github/actions/workflow/status/huangsam/java-trial/ci.yml)](https://github.com/huangsam/java-trial/actions)

Playground to assess new features and popular libraries on Java LTS versions.

<img src="images/java-trial.webp" alt="Java logo" width="250px" />

## Getting started

Here are the commands you need to know:

```bash
# Run application
./gradlew run

# Run tests
./gradlew test
```

## Project structure

This repository is structured into thematic packages to evaluate different aspects of the Java ecosystem:

- `stdlib`: Foundational JDK utilities (Time, Math, Collections).
- `features.modern`: In-depth exploration of stable Java LTS features (Records, Patterns).
- `libs`: Assessment of 3rd party libraries (Gson, Guava).
- `concurrent`: Focused trials for Multithreading and Virtual Threads.

For detailed technical standards and naming conventions, refer to the [Agent Guide](AGENTS.md).

## Useful resources

- **JDK Features**: <https://javaalmanac.io/features/>
- **LTS Docs**: [JDK 17](https://docs.oracle.com/en/java/javase/17/), [JDK 21](https://docs.oracle.com/en/java/javase/21/), [JDK 25](https://docs.oracle.com/en/java/javase/25/)
- **Awesome Java**: <https://github.com/akullpp/awesome-java>
