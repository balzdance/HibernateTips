# CriteriaQueryPagination

**Kernidee:** Zeigt, wie man die Ergebnismenge einer Criteria-Query seitenweise (Pagination) lädt, um große Datenmengen in Blöcken abzufragen.

**Zentrale Annotation / API:** `em.createQuery(cq).setFirstResult(...).setMaxResults(...)`

**Im Test:** Lädt mit einer nach `Book_.id` sortierten `CriteriaQuery<Book>` zunächst die ersten 5 Bücher (`setFirstResult(0)`) und dann die nächsten 5 (`setFirstResult(5)`).

**Gut zu wissen:** Eine stabile `orderBy`-Sortierung ist Voraussetzung für reproduzierbare Seiten; ohne sie ist die Reihenfolge undefiniert.
