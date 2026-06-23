# JPQLDelete

**Kernidee:** Zeigt das Bulk-Delete per JPQL: Mehrere Datensätze werden mit einem einzigen `DELETE`-Statement entfernt, ohne sie vorher als Entitäten zu laden.

**Zentrale Annotation / API:** `em.createQuery("DELETE FROM Book b")` mit `query.executeUpdate()`.

**Im Test:** Loggt die vorhandenen Buch-Titel, löscht alle `Book`-Datensätze per JPQL-Delete und loggt danach die (leere) Liste erneut.

**Gut zu wissen:** Bulk-Operationen umgehen den Persistence Context und lösen keine Lifecycle-Callbacks oder Kaskaden aus; bereits geladene Entitäten im EntityManager bleiben veraltet und sollten ggf. detached/refreshed werden.
