# Failing AspectJ transactions

See https://stackoverflow.com/questions/71855704/why-is-spring-transactional-unreliable-with-aspectj

## Instructions

There are two test classes.
To reproduce the issue, `TestPrep` must run before the actual test `TestTransaction` in the same run.
Note that to avoid the test execution order changing, `TestPrep` has been rigged to always fail.

- Run the gradle `test` task. `TestTransaction` will fail.
- Run `TestTransaction` singly from an IDE or remove `TestPrep`, `TestTransaction` passes. 

## Observations

- Issue only occurs if build.gradle.kts contains `exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL`
- Issue only occurs if `TestTransaction` has a `@TestPropertySource`

## Environment

- Ubuntu 20.04.4 LTS
- Java Temurin 17.0.2
- IntelliJ IDEA 2022.1
