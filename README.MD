# Failing AspectJ transactions

See https://stackoverflow.com/questions/71855704/why-is-spring-transactional-unreliable-with-aspectj

## Instructions

There are two test classes.
To reproduce the issue, `TestPrep` must run before the actual test `TestTransaction` in the same run.
Note that to avoid the test execution order changing, `TestPrep` has been rigged to always fail.

- Run the gradle `test` task. `TestTransaction` will fail.
- Run `TestTransaction` singly from an IDE or remove `TestPrep`, `TestTransaction` passes.

## Observations

- Issue does not occur using standard (non-AspectJ) Spring transactions e.g.
  - In `build.gradle.kts` comment out `id("io.freefair.aspectj.post-compile-weaving") version "6.4.1"` and `aspect("org.springframework:spring-aspects")`
  - In `AspectjTxApplication.kt` change `@EnableTransactionManagement(mode = AdviceMode.ASPECTJ)` to `@EnableTransactionManagement`
- Issue only occurs if `TestTransaction` has a `@TestPropertySource`
- If a debug point is added to `Service2` before throwing the exception, it can be observed that the update is already visible in the database (i.e. outside of the supposed transaction).

## Environment

- Ubuntu 20.04.4 LTS
- Java Temurin 17.0.2
- IntelliJ IDEA 2022.1
