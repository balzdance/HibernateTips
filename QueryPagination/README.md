# QueryPagination

**Kernidee:** Demonstriert das seitenweise Laden von Ergebnissen (Pagination) über die Standard-JPA-API, um große Ergebnismengen in Blöcken abzufragen.

**Zentrale Annotation / API:** `Query.setFirstResult(offset)` und `Query.setMaxResults(limit)`

**Im Test:** Legt 10 Autoren an und liest sortiert nach `id` zuerst die ersten 5 (`firstResult 0`), dann die nächsten 5 (`firstResult 5`).

**Gut zu wissen:** Für deterministische Paginierung immer ein `ORDER BY` setzen, sonst ist die Reihenfolge der Seiten nicht garantiert.
