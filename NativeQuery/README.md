# NativeQuery

**Kernidee:** Zeigt, wie eine ad-hoc native SQL-Abfrage direkt zur Laufzeit ausgeführt und ihr Ergebnis auf ein Entity gemappt wird.

**Zentrale Annotation / API:** `em.createNativeQuery("SELECT * FROM book b WHERE id = ?", Book.class)` mit `setParameter(1, ...)`

**Im Test:** Es wird per native Query ein Buch über seinen Positionsparameter geladen und geprüft, dass das Ergebnis ein `Book` mit id=1 ist.

**Gut zu wissen:** Durch Angabe der `resultClass` liefert die native Query verwaltete Entities statt `Object[]`. Native Queries sind datenbankabhängig und umgehen JPQL/HQL.
