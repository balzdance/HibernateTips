# JPQLCustomFunction

**Kernidee:** Zeigt, wie man in Hibernate 6 eine eigene SQL-Funktion (`calculate(x, y)`) registriert und sie anschließend per `function(...)` in JPQL aufruft.

**Zentrale Annotation / API:** `FunctionContributor`-SPI in `CalculateFunctionContributor`, Registrierung über `registry.patternDescriptorBuilder("calculate", "(?1+?2)")`; Aufruf via `function('calculate', b.price, :double1)` im JPQL.

**Im Test:** Filtert Bücher mit `WHERE :double2 > function('calculate', b.price, :double1)` und gibt die Treffer aus.

**Gut zu wissen:** Der Contributor wird in Hibernate 6 über die Service-Datei `META-INF/services/org.hibernate.boot.model.FunctionContributor` automatisch entdeckt (löst die alten `MetadataBuilderContributor`/Dialect-Mechanismen ab); hier auf H2 als reines SQL-Pattern statt PostgreSQL-Stored-Function.
