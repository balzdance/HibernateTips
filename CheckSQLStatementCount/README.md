# CheckSQLStatementCount

**Kernidee:** Zeigt, wie man mit der Hibernate Statistics API die Anzahl ausgeführter SQL-Statements zählt, um z. B. n+1-Select-Probleme aufzudecken.

**Zentrale Annotation / API:** `emf.unwrap(SessionFactory.class).getStatistics()`, `Statistics.getQueryExecutionCount()` / `getCollectionFetchCount()`

**Im Test:** Lädt alle `Author`-Objekte und greift auf deren `books`-Collection zu; danach werden Query- und Collection-Fetch-Count ausgelesen und geloggt.

**Gut zu wissen:** Die Statistik muss in der `persistence.xml` aktiviert sein (`hibernate.generate_statistics`). Der Zugriff auf die lazy `books`-Collection pro Author löst die zusätzlichen Fetches aus, die sich in der Statistik widerspiegeln.
