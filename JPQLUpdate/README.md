# JPQLUpdate

**Kernidee:** Zeigt ein Bulk-Update per JPQL, das viele Datensätze mit einem einzigen SQL-UPDATE ändert, ohne die Entities einzeln zu laden. Effizient für Massenänderungen.

**Zentrale Annotation / API:** `em.createQuery("UPDATE Book b SET b.price = b.price*1.1").executeUpdate()`

**Im Test:** Loggt alle Buchpreise, erhöht sie per Bulk-Update um 10 % und loggt die neuen Preise.

**Gut zu wissen:** Bulk-Updates umgehen den Persistence Context; bereits geladene Entities werden nicht automatisch aktualisiert und sollten ggf. per `refresh()` neu geladen werden.
