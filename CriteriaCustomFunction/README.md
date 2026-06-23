# CriteriaCustomFunction

**Kernidee:** Zeigt, wie man eine eigene SQL-Funktion registriert und in einer Criteria-Query über `cb.function(...)` aufruft.

**Zentrale Annotation / API:** `FunctionContributor.contributeFunctions(...)` mit `getFunctionRegistry().registerPattern("calculate", "(?1 + ?2)")`, im Test `cb.function("calculate", Double.class, ...)`

**Im Test:** Ruft die eigene Funktion `calculate(price, param)` in der `where`-Bedingung auf und filtert `Book`-Objekte über zwei `Double`-Parameter.

**Gut zu wissen:** In Hibernate 6 erfolgt die Registrierung über die `FunctionContributor`-SPI: `CalculateFunctionContributor` wird per `META-INF/services/org.hibernate.boot.model.FunctionContributor` gefunden. Das ersetzt die in Hibernate 5 übliche Dialect-/`MetadataBuilder`-Registrierung.
