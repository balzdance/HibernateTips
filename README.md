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

## Modulübersicht

Jede Zeile verlinkt auf die Kurz-Notiz (Kernidee, zentrale Annotation/API, Test, Stolpersteine) im jeweiligen Modul.

### Grundlagen & Attribut-Mapping
| Modul | Kernidee |
| --- | --- |
| [MapBasicAttributes](MapBasicAttributes/) | Standard-Mapping einfacher Attribute auf Spalten (inkl. Spaltenname). |
| [DateAndTime](DateAndTime/) | `java.time`-Typen (`LocalDate`) ohne `@Temporal` direkt mappen. |
| [MapUtilDate](MapUtilDate/) | Klassisches `java.util.Date` mit `@Temporal`-Granularität mappen. |
| [MapEnumerations](MapEnumerations/) | Java-Enum als lesbaren String statt Ordinalzahl persistieren. |
| [CustomEnumerationsMapping](CustomEnumerationsMapping/) | Enum per `AttributeConverter` auf frei gewählte DB-Werte abbilden. |
| [AttributeConverterForDateAndTime](AttributeConverterForDateAndTime/) | `AttributeConverter` für automatische Typkonvertierung. |
| [TransientAttributes](TransientAttributes/) | `@Transient`: berechnete/abgeleitete Felder nicht persistieren. |
| [Formula](Formula/) | Berechnetes Attribut beim Lesen per SQL-Ausdruck (`@Formula`). |
| [MapGeneratedColumns](MapGeneratedColumns/) | DB-generierte Spalte nach INSERT/UPDATE automatisch zurücklesen. |
| [DefineTableAndSchemaName](DefineTableAndSchemaName/) | Tabellen- und Schemaname explizit per `@Table` setzen. |

### Primärschlüssel & ID-Generierung
| Modul | Kernidee |
| --- | --- |
| [PrimaryKey](PrimaryKey/) | Einfachster `@Id`: Wert von der Anwendung selbst vergeben. |
| [PrimaryKeyIdentityStrategy](PrimaryKeyIdentityStrategy/) | Schlüssel aus DB-Auto-Increment (`GenerationType.IDENTITY`). |
| [PrimaryKeyTableStrategy](PrimaryKeyTableStrategy/) | ID-Generierung über separate Tabelle (`GenerationType.TABLE`). |
| [PrimaryKeyUUID](PrimaryKeyUUID/) | UUID-Schlüssel mit Hibernate-6 `@UuidGenerator` (V4 & V1). |
| [CustomSequence](CustomSequence/) | Eigene DB-Sequenz per `@SequenceGenerator`. |
| [DerivedPrimaryKey](DerivedPrimaryKey/) | Abgeleiteter Schlüssel aus Beziehung (`@MapsId`, `@EmbeddedId`). |
| [HibernateNaturalId](HibernateNaturalId/) | Fachlich eindeutiges Attribut als `@NaturalId` laden. |
| [HibernateMultipleId](HibernateMultipleId/) | Viele Entities per ID-Liste in einem Aufruf laden. |

### Assoziationen
| Modul | Kernidee |
| --- | --- |
| [AssociationUnidirectionalOneToOne](AssociationUnidirectionalOneToOne/) | Unidirektionale `@OneToOne`. |
| [AssociationBidirectionalOneToOne](AssociationBidirectionalOneToOne/) | Bidirektionale `@OneToOne` (`mappedBy`). |
| [AssociationUnidirectionalManyToOne](AssociationUnidirectionalManyToOne/) | Unidirektionale `@ManyToOne` (effizienteste Form). |
| [AssociationBidirectionalManyToOne](AssociationBidirectionalManyToOne/) | Bidirektionale `@OneToMany`/`@ManyToOne`. |
| [AssociationUnidirectionalOneToMany](AssociationUnidirectionalOneToMany/) | Unidirektionale `@OneToMany` (mit Effizienz-Hinweis). |
| [AssociationUnidirectionalManyToMany](AssociationUnidirectionalManyToMany/) | Unidirektionale `@ManyToMany` über Join-Tabelle. |
| [AssociationBidirectionalManyToMany](AssociationBidirectionalManyToMany/) | Bidirektionale `@ManyToMany` mit Helfermethoden. |
| [AssociationsWithAttributes](AssociationsWithAttributes/) | Join-Tabelle mit Attributen als eigene Assoziations-Entity. |
| [MapOptionalAssociations](MapOptionalAssociations/) | Optionale `@ManyToOne` über `Optional<…>`-Getter. |
| [OrderRelationships](OrderRelationships/) | To-many-Beziehung beim Laden automatisch sortieren (`@OrderBy`). |
| [CascadePersist](CascadePersist/) | Persist über Assoziationen kaskadieren (`CascadeType.PERSIST`). |

### Vererbung
| Modul | Kernidee |
| --- | --- |
| [InheritanceSingleTable](InheritanceSingleTable/) | Ganze Hierarchie in einer Tabelle (`SINGLE_TABLE`). |
| [InheritanceJoined](InheritanceJoined/) | Tabelle pro Klasse mit FK-Join (`JOINED`). |
| [InheritanceTablePerClass](InheritanceTablePerClass/) | Eigene volle Tabelle pro konkreter Klasse (`TABLE_PER_CLASS`). |
| [InheritanceMappedSuperclass](InheritanceMappedSuperclass/) | `@MappedSuperclass`: nur Mapping vererben, keine Entity. |
| [Treat](Treat/) | `TREAT`-Operator zum Downcast auf Subtyp in Queries. |

### JPQL
| Modul | Kernidee |
| --- | --- |
| [JPQLAdHocQuery](JPQLAdHocQuery/) | Ad-hoc-JPQL mit benanntem Parameter. |
| [JPQLNamedQuery](JPQLNamedQuery/) | Vordefinierte, beim Start validierte `@NamedQuery`. |
| [JPQLConstructorExpression](JPQLConstructorExpression/) | Direkt in DTO projizieren (`new …(…)`). |
| [JPQLScalarValues](JPQLScalarValues/) | Nur Skalarwerte statt ganzer Entities laden. |
| [JpqlStandardFunction](JpqlStandardFunction/) | Standardfunktion `size()` für Beziehungs-Count. |
| [JPQLCustomFunction](JPQLCustomFunction/) | Eigene SQL-Funktion via `FunctionContributor` (Hibernate 6). |
| [JPQLUpdate](JPQLUpdate/) | Bulk-Update mit einem `UPDATE`-Statement. |
| [JPQLDelete](JPQLDelete/) | Bulk-Delete mit einem `DELETE`-Statement. |

### Criteria API
| Modul | Kernidee |
| --- | --- |
| [CriteriaQuery](CriteriaQuery/) | Typsichere Criteria-Query mit Join & Parameter. |
| [CriteriaConstructor](CriteriaConstructor/) | Ergebnis in DTO/POJO projizieren (Constructor Expression). |
| [CriteriaTuples](CriteriaTuples/) | Spaltenprojektion als `Tuple`. |
| [CriteriaQueryPagination](CriteriaQueryPagination/) | Seitenweises Laden (Pagination). |
| [CriteriaStandardFunction](CriteriaStandardFunction/) | Aggregation per Standardfunktion (`size`). |
| [CriteriaCustomFunction](CriteriaCustomFunction/) | Eigene SQL-Funktion via `cb.function(...)`. |
| [CriteriaUpdate](CriteriaUpdate/) | Typsicheres Bulk-Update. |
| [CriteriaDelete](CriteriaDelete/) | Typsicheres Bulk-Delete. |
| [JPAMetamodel](JPAMetamodel/) | Generiertes statisches Metamodell (`Book_`) für Typsicherheit. |

### Native Queries, Stored Procedures & Views
| Modul | Kernidee |
| --- | --- |
| [NativeQuery](NativeQuery/) | Ad-hoc native SQL-Abfrage, Ergebnis auf Entity gemappt. |
| [NamedNativeQuery](NamedNativeQuery/) | Benannte native Query an der Entity (`@NamedNativeQuery`). |
| [MapNativeQueryToEntity](MapNativeQueryToEntity/) | Native-Query-Ergebnis auf Entity mappen (`@SqlResultSetMapping`). |
| [MapNativeQueryToPojo](MapNativeQueryToPojo/) | Native-Query-Ergebnis auf POJO mappen (`@ConstructorResult`). |
| [StoredProcedureQuery](StoredProcedureQuery/) | Stored Procedure aufrufen (ad hoc & benannt). |
| [DatabaseViews](DatabaseViews/) | Read-only DB-View per `@Subselect` auf Entity mappen. |

### Performance: Fetching, Caching & Queries
| Modul | Kernidee |
| --- | --- |
| [JoinFetch](JoinFetch/) | `JOIN FETCH` gegen `LazyInitializationException` / N+1. |
| [EntityGraph](EntityGraph/) | Pro Query festlegen, was eager geladen wird. |
| [2ndLevelCache](2ndLevelCache/) | Second-Level-Cache für Entities über Sessions hinweg. |
| [QueryCache](QueryCache/) | Query-Cache für wiederholte identische Abfragen. |
| [CheckSQLStatementCount](CheckSQLStatementCount/) | SQL-Statements per Statistics API zählen (N+1 aufdecken). |
| [QueryPagination](QueryPagination/) | Pagination über die Standard-JPA-API. |
| [QueryTimeout](QueryTimeout/) | Timeout pro Abfrage setzen. |
| [ResultsAsStreams](ResultsAsStreams/) | Ergebnisse als `Stream` statt `List` abrufen. |

### Lifecycle, Logging, Bootstrapping & API-Zugriff
| Modul | Kernidee |
| --- | --- |
| [PrePersistLifecycleEvent](PrePersistLifecycleEvent/) | `@PrePersist`-Callback füllt Feld vor dem Speichern. |
| [LogSQLStatements](LogSQLStatements/) | Generiertes SQL per Konfiguration sichtbar machen. |
| [CommentSQLStatements](CommentSQLStatements/) | SQL-Statements mit eigenem Kommentar versehen. |
| [AccessHibernateApi](AccessHibernateApi/) | Von JPA zur nativen Hibernate-API absteigen (`unwrap`). |
| [JPABootstrapping](JPABootstrapping/) | Standard-JPA-Bootstrapping (`EntityManagerFactory`). |
| [HibernateBootstrapping](HibernateBootstrapping/) | Programmatisches Bootstrapping der `SessionFactory`. |
| [HibernateJoinUnassociatedEntities](HibernateJoinUnassociatedEntities/) | Nicht-assoziierte Entities in JPQL joinen. |
| [SpringBootBootstrapping](SpringBootBootstrapping/) | JPA/Hibernate mit Spring Boot 3 & Spring Data JPA. |

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
