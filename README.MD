# Failing AspectJ transactions

See https://stackoverflow.com/questions/71855704/why-is-spring-transactional-unreliable-with-aspectj

## DB setup

Assumes a postgresql running on localhost with credentials postgres/password.

e.g. `docker run --rm -d -p 5432:5432 -e POSTGRES_PASSWORD=password --name=postgres postgres:12.6`

## Instructions

There are two test classes.
To reproduce the issue, `TestPrep` must run before the actual test `TestTransaction` in the same run.
Note that to avoid the test execution order changing, `TestPrep` has been rigged to always fail.

- Run the gradle `test` task. `TestTransaction` will fail.
- Run `TestTransaction` singly from an IDE or remove `TestPrep`, `TestTransaction` passes.

## Observations

- Issue only occurs if `TestTransaction` has a `@TestPropertySource`
- If a debug point is added to `Service2` before throwing the exception, it can be observed that the update is already visible in the database (i.e. outside of the supposed transaction).

## Environment

- Ubuntu 20.04.4 LTS
- Java Temurin 17.0.2
- IntelliJ IDEA 2022.1
