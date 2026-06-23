# JPQLAdHocQuery

**Kernidee:** Zeigt eine zur Laufzeit zusammengesetzte (ad-hoc) JPQL-Abfrage mit benanntem Parameter, die direkt Entitäten zurückliefert.

**Zentrale Annotation / API:** `em.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class)` mit `q.setParameter("id", 1L)` und `getSingleResult()`.

**Im Test:** Führt die JPQL-Query aus, liest genau ein `Book` und prüft Typ und ID des Ergebnisses.

**Gut zu wissen:** `getSingleResult()` wirft eine Exception, wenn kein oder mehr als ein Treffer zurückkommt; benannte Parameter (`:id`) schützen vor SQL-Injection.
