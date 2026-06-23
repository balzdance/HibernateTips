# QueryTimeout

**Kernidee:** Zeigt, wie sich für einzelne Abfragen ein Timeout setzen lässt, damit lang laufende Queries abgebrochen werden statt zu blockieren.

**Zentrale Annotation / API:** Hint `jakarta.persistence.query.timeout` (Wert in Sekunden) – per `Query.setHint(...)` oder als Hint-Map bei `EntityManager.find(...)`

**Im Test:** Setzt den Timeout-Hint einmal auf einer JPQL-Query und einmal beim `em.find()`-Aufruf.

**Gut zu wissen:** In Hibernate 6 / Jakarta Persistence heißt der Hint `jakarta.persistence.query.timeout` (zuvor `javax.persistence...`); die tatsächliche Durchsetzung hängt vom JDBC-Treiber bzw. der Datenbank ab.
