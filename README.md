# Hibernate Tips — modernized

A personal learning fork of Thorben Janssen's **Hibernate Tips** recipe collection.
Each subdirectory is a small, self-contained Maven module that demonstrates exactly
one Hibernate / JPA feature (mappings, JPQL, the Criteria API, caching, inheritance,
associations, native queries, …).

The original code targeted **Hibernate 5.2 / JPA 2.1 / `javax.persistence` / Java 8**
and required a running PostgreSQL instance. This fork is being modernized to a current,
zero-setup stack so the examples can be studied and run anywhere.

## Modernized stack

| Aspect            | Original                     | Now                                            |
| ----------------- | ---------------------------- | ---------------------------------------------- |
| Hibernate ORM     | 5.2.8.Final                  | 6.6.4.Final (`org.hibernate.orm`)              |
| Persistence API   | `javax.persistence` (JPA 2.1)| `jakarta.persistence` 3.1                       |
| Java              | 8                            | 21                                             |
| Tests             | JUnit 4                      | JUnit 5 (Jupiter)                              |
| Database (tests)  | PostgreSQL (external)        | H2 in-memory (no DB / Docker needed)           |
| Logging           | log4j 1.2                    | SLF4J + slf4j-simple                           |

> Why H2 instead of Testcontainers? This is a learning resource meant to run with a
> plain `mvn test`, with no Docker daemon required. Modules that rely on
> PostgreSQL-specific behaviour are noted as they are migrated; for those, swapping the
> H2 dependency for `testcontainers:postgresql` is straightforward.

## Requirements

- JDK 21+
- Maven 3.9+

## Running an example

Each recipe is its own module. Build and run the test for a single module:

```bash
mvn -pl DateAndTime test
```

The test bootstraps an `EntityManagerFactory` against an in-memory H2 database
(`drop-and-create` schema generation), so it is fully self-contained. SQL is logged to
the console (`hibernate.show_sql` in each module's `persistence.xml`).

## Migration progress

**All 73 modules have been migrated** to the modern stack and are registered in the
parent `pom.xml`. A full reactor build (`mvn test`) is green — 95 tests pass against
in-memory H2, no external database required.

Most modules were a mechanical application of the recipe below. A handful needed real
Hibernate 6 / H2 adaptations worth knowing about:

| Module(s) | Adaptation |
| --- | --- |
| `CriteriaCustomFunction`, `JPQLCustomFunction` | Custom SQL function registration moved from the removed `MetadataBuilder.applySqlFunction` to the Hibernate 6 `FunctionContributor` SPI (registered via `META-INF/services`). |
| `2ndLevelCache`, `QueryCache` | Ehcache 2 integration is gone; switched to JCache + Ehcache 3.10 (`jakarta` classifier), region factory `jcache`. Ehcache pulls a legacy javax JAXB range that must be excluded (Hibernate already provides Jakarta JAXB). |
| `MapGeneratedColumns` | PostgreSQL trigger replaced with an H2 `org.h2.api.Trigger`; `@Generated(GenerationTime)` updated to the Hibernate 6 `@Generated(event = …)` form. |
| `StoredProcedureQuery` | PostgreSQL PL/pgSQL function (with `OUT` param) recreated as an H2 `CREATE ALIAS` to a static Java method, invoked via Hibernate's `ProcedureCall.markAsFunctionCall`. |
| `DatabaseViews` | PostgreSQL `CREATE VIEW` replaced with a portable `@Subselect` + `@Synchronize` (H2 `GROUP_CONCAT` instead of `string_agg`). |
| `Formula` | `@Formula` rewritten from PostgreSQL `age()`/`date_part` to H2 `datediff` against a fixed reference date (kept deterministic). |
| `PrimaryKeyUUID` | Deprecated `@GenericGenerator(UUIDGenerator)` replaced with Hibernate 6 `@UuidGenerator`. |
| `SpringBootBootstrapping` | Upgraded to Spring Boot 3.3 (Jakarta, Hibernate 6), H2 datasource, JUnit 5 test slice. |

Recurring mechanical fixes applied throughout: `javax.persistence.*` → `jakarta.persistence.*`,
JUnit 4 → 5, log4j → SLF4J (including `log.info(entity)` → `log.info("{}", entity)` since
SLF4J needs a `String` first arg), `new Long(x)` → `Long.valueOf(x)` (the boxing
constructors are gone in Java 21), and renaming `javax.persistence.*` properties (e.g.
`sql-load-script-source`, query hints) to `jakarta.persistence.*`.

## Migration recipe (per module)

When porting a module from the original 5.2 / javax stack, apply this checklist:

1. **`pom.xml`** — depend on `org.hibernate.orm:hibernate-core`,
   `jakarta.persistence:jakarta.persistence-api`, `org.slf4j:slf4j-api`, and (test scope)
   `org.junit.jupiter:junit-jupiter`, `com.h2database:h2`, `org.slf4j:slf4j-simple`.
   Versions and the JPA metamodel annotation processor come from the parent POM.
2. **`persistence.xml`** — namespace `https://jakarta.ee/xml/ns/persistence` version 3.1;
   `H2Dialect`; H2 in-memory JDBC URL; rename `javax.persistence.*` properties to
   `jakarta.persistence.*`.
3. **Entities / main code** — replace `javax.persistence.*` imports with
   `jakarta.persistence.*`. Review any deprecated Hibernate APIs.
4. **Tests** — JUnit 4 → 5 (`org.junit.jupiter.api`, `@BeforeEach`/`@AfterEach`/`@Test`,
   `Assertions.*`); swap log4j `Logger` for SLF4J.
5. **Logging** — replace `src/test/resources/log4j.properties` with
   `simplelogger.properties`.
6. Add the module to the parent `<modules>` list and verify with `mvn -pl <Module> test`.

## Credits

Original examples © Thorben Janssen, MIT License (see `LICENSE`). This fork keeps the MIT
license and is maintained for personal learning.
