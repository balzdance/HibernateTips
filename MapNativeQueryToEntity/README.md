# MapNativeQueryToEntity

**Kernidee:** Zeigt, wie das Ergebnis einer nativen SQL-Query auf eine Entity gemappt wird – sowohl implizit als auch explizit über ein definiertes Mapping.

**Zentrale Annotation / API:** `em.createNativeQuery(sql, Book.class)` bzw. `em.createNativeQuery(sql, "BookMapping")` mit `@SqlResultSetMapping` (`@EntityResult` / `@FieldResult`)

**Im Test:** `implicitMapping` mappt `SELECT *` direkt auf `Book.class`; `explicitMapping` nutzt das benannte `BookMapping`, das Spalten-Aliase (z. B. `bookId`) auf Entity-Felder abbildet.

**Gut zu wissen:** Beim expliziten Mapping müssen die Spalten-Aliase der Query exakt zu den `@FieldResult.column`-Werten passen.
