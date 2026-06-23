# StoredProcedureQuery

**Kernidee:** Zeigt den Aufruf einer Stored Procedure aus JPA/Hibernate – sowohl ad hoc als auch als benannte Query – samt IN-Parametern und Rückgabewert.

**Zentrale Annotation / API:** `EntityManager.createStoredProcedureQuery(...)` bzw. `createNamedStoredProcedureQuery(...)` mit `@NamedStoredProcedureQuery` und `@StoredProcedureParameter`

**Im Test:** Ruft die Prozedur `calculate(x, y)` mit zwei `Double`-IN-Parametern auf und erwartet als Ergebnis 5.23.

**Gut zu wissen:** Auf H2 gibt es keine echten Stored Procedures mit OUT-Parametern; die Prozedur wird per `CREATE ALIAS` an die statische Java-Methode `Calculator.calculate` gebunden und im Test über `query.unwrap(ProcedureCall.class).markAsFunctionCall(Types.DOUBLE)` als wertliefernde Funktion (`{ ? = call calculate(?, ?) }`) behandelt.
