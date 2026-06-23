# HibernateMultipleId

**Kernidee:** Zeigt das effiziente Laden mehrerer Entities anhand einer Liste von Primärschlüsseln in einem einzigen Aufruf (statt vieler einzelner `find`).

**Zentrale Annotation / API:** Hibernate `Session.byMultipleIds(Book.class)` → `MultiIdentifierLoadAccess`, dann `.multiLoad(1L, 2L, 3L)`; optional `.withBatchSize(n)` und `.enableSessionCheck(true)`.

**Im Test:** Drei Varianten laden die Bücher mit IDs 1–3 und prüfen, dass 3 Entities zurückkommen – einmal einfach, einmal mit Batch-Size 2, einmal mit Session-Check nach vorherigem `find`.

**Gut zu wissen:** `withBatchSize` splittet die IDs in mehrere SQL-Statements; `enableSessionCheck(true)` nutzt bereits im Persistence Context geladene Entities, statt sie erneut aus der DB zu lesen.
