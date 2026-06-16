# Hibernate Tips

A collection of small, self-contained examples that each demonstrate a single
Hibernate or JPA feature. Every example is a standalone Maven module with a
JUnit test that shows the feature in action, so you can run it, read the
generated SQL and adapt it to your own project.

The examples accompany the *Hibernate Tips* series and use:

- **Hibernate** 5.2.8.Final
- **JPA** 2.1
- **Java** 8
- **JUnit** 4 with an in-memory **H2** database

## Getting started

### Prerequisites

- JDK 8 (the modules target `1.8`)
- Maven 3

### Build everything

```bash
mvn clean install
```

This builds the parent project (`org.thoughts.on.java.recipes:HibernateTips`)
and all example modules.

### Run a single example

Each module contains a JUnit test that demonstrates the feature. To build and
run just one example:

```bash
mvn test -pl PrimaryKey
```

Most tests log the SQL statements Hibernate sends to the database, so you can
see exactly what each feature does under the hood.

### Project layout

Every module follows the standard Maven layout:

```
<Example>/
├── pom.xml
├── src/main/java/org/thoughts/on/java/model/   # entities & mappings
├── src/main/resources/META-INF/persistence.xml # persistence unit
└── src/test/java/org/thoughts/on/java/model/    # JUnit test demonstrating the tip
```

## Examples by topic

### Primary keys & ID generation

| Module | Topic |
| --- | --- |
| `PrimaryKey` | Basic primary key mapping |
| `PrimaryKeyIdentityStrategy` | `GenerationType.IDENTITY` |
| `PrimaryKeyTableStrategy` | `GenerationType.TABLE` |
| `PrimaryKeyUUID` | UUID primary keys |
| `CustomSequence` | Custom database sequence |
| `HibernateNaturalId` | Natural IDs |
| `HibernateMultipleId` | Composite / multiple IDs |
| `DerivedPrimaryKey` | Derived primary keys (`@MapsId`) |

### Associations & relationships

| Module | Topic |
| --- | --- |
| `AssociationUnidirectionalOneToOne` | Unidirectional one-to-one |
| `AssociationBidirectionalOneToOne` | Bidirectional one-to-one |
| `AssociationUnidirectionalOneToMany` | Unidirectional one-to-many |
| `AssociationUnidirectionalManyToOne` | Unidirectional many-to-one |
| `AssociationBidirectionalManyToOne` | Bidirectional many-to-one |
| `AssociationUnidirectionalManyToMany` | Unidirectional many-to-many |
| `AssociationBidirectionalManyToMany` | Bidirectional many-to-many |
| `AssociationsWithAttributes` | Association tables with extra attributes |
| `MapOptionalAssociations` | Optional associations |
| `OrderRelationships` | Ordering related entities |
| `JoinFetch` | `JOIN FETCH` to avoid lazy-loading issues |
| `HibernateJoinUnassociatedEntities` | Joining unassociated entities |

### Inheritance

| Module | Topic |
| --- | --- |
| `InheritanceSingleTable` | `SINGLE_TABLE` strategy |
| `InheritanceJoined` | `JOINED` strategy |
| `InheritanceTablePerClass` | `TABLE_PER_CLASS` strategy |
| `InheritanceMappedSuperclass` | `@MappedSuperclass` |
| `Treat` | `TREAT` operator for polymorphic queries |

### Mapping basics & attributes

| Module | Topic |
| --- | --- |
| `MapBasicAttributes` | Basic attribute mapping |
| `MapEnumerations` | Mapping enums |
| `CustomEnumerationsMapping` | Custom enum mapping |
| `MapGeneratedColumns` | Database-generated columns |
| `TransientAttributes` | Transient attributes |
| `Formula` | `@Formula` calculated values |
| `DateAndTime` | Mapping `java.time` types |
| `MapUtilDate` | Mapping `java.util.Date` |
| `AttributeConverterForDateAndTime` | `AttributeConverter` for date/time |
| `DefineTableAndSchemaName` | Defining table & schema names |
| `DatabaseViews` | Mapping database views |

### JPQL

| Module | Topic |
| --- | --- |
| `JPQLAdHocQuery` | Ad-hoc JPQL queries |
| `JPQLNamedQuery` | Named queries |
| `JPQLConstructorExpression` | Constructor expressions / DTO projection |
| `JPQLScalarValues` | Selecting scalar values |
| `JPQLCustomFunction` | Custom functions in JPQL |
| `JpqlStandardFunction` | Standard JPQL functions |
| `JPQLDelete` | Bulk delete |
| `JPQLUpdate` | Bulk update |

### Criteria API

| Module | Topic |
| --- | --- |
| `CriteriaQuery` | Basic Criteria query |
| `CriteriaConstructor` | Constructor expressions |
| `CriteriaTuples` | `Tuple` result type |
| `CriteriaCustomFunction` | Custom functions |
| `CriteriaStandardFunction` | Standard functions |
| `CriteriaDelete` | Bulk delete |
| `CriteriaUpdate` | Bulk update |
| `CriteriaQueryPagination` | Pagination with the Criteria API |
| `JPAMetamodel` | Using the JPA metamodel |

### Native queries & stored procedures

| Module | Topic |
| --- | --- |
| `NativeQuery` | Native SQL queries |
| `NamedNativeQuery` | Named native queries |
| `MapNativeQueryToEntity` | Mapping native query results to entities |
| `MapNativeQueryToPojo` | Mapping native query results to POJOs |
| `StoredProcedureQuery` | Calling stored procedures |

### Query features & performance

| Module | Topic |
| --- | --- |
| `QueryPagination` | Pagination |
| `QueryTimeout` | Query timeouts |
| `QueryCache` | Query cache |
| `2ndLevelCache` | Second-level cache |
| `ResultsAsStreams` | Returning results as a `Stream` |

### SQL logging & debugging

| Module | Topic |
| --- | --- |
| `LogSQLStatements` | Logging SQL statements |
| `CommentSQLStatements` | Adding comments to SQL statements |
| `CheckSQLStatementCount` | Asserting the number of executed statements |

### Lifecycle, cascading & the Hibernate API

| Module | Topic |
| --- | --- |
| `PrePersistLifecycleEvent` | `@PrePersist` lifecycle callbacks |
| `CascadePersist` | Cascading persist operations |
| `AccessHibernateApi` | Accessing the Hibernate API from JPA |

### Bootstrapping

| Module | Topic |
| --- | --- |
| `JPABootstrapping` | Bootstrapping with plain JPA |
| `HibernateBootstrapping` | Bootstrapping the native Hibernate API |
| `SpringBootBootstrapping` | Bootstrapping with Spring Boot |

## License

Released under the [MIT License](LICENSE). Copyright (c) 2016 Thorben Janssen.
