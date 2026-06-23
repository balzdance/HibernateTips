# CriteriaUpdate

**Kernidee:** Zeigt ein typsicheres Bulk-Update mehrerer Datensätze über die Criteria API, ohne die Entities einzeln zu laden.

**Zentrale Annotation / API:** `cb.createCriteriaUpdate(Book.class)`, `update.set(...)`, `cb.prod(...)` und `query.executeUpdate()`

**Im Test:** Erhöht in einem einzigen SQL-UPDATE den Preis aller `Book`-Entities um 10 % (`price * 1.1`) und loggt die Preise davor und danach.

**Gut zu wissen:** Ein CriteriaUpdate läuft direkt auf der Datenbank vorbei am Persistence Context; bereits geladene Entities im Cache werden nicht automatisch aktualisiert.
