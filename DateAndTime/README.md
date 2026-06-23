# DateAndTime

**Kernidee:** Zeigt, dass moderne `java.time`-Typen (hier `LocalDate`) seit JPA 2.2 / Hibernate ohne Zusatzkonfiguration direkt als Entity-Attribut gemappt werden können.

**Zentrale Annotation / API:** schlichtes `private LocalDate publishingDate;` ohne `@Temporal` oder Converter

**Im Test:** Persistiert ein `Book` mit `publishingDate = LocalDate.of(2017, 4, 4)`, lädt es in einer zweiten Transaktion erneut und prüft, dass das Datum unverändert ist.

**Gut zu wissen:** Bei `java.time`-Typen ist die früher übliche `@Temporal`-Annotation (für `java.util.Date`) weder nötig noch erlaubt.
