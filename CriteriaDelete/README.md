# CriteriaDelete

**Kernidee:** Zeigt, wie man mit der Criteria API eine Bulk-Delete-Operation typsicher und ohne JPQL-String formuliert.

**Zentrale Annotation / API:** `CriteriaBuilder.createCriteriaDelete(Book.class)`, `delete.from(...)`, Ausführung über `em.createQuery(delete).executeUpdate()`

**Im Test:** Loggt zunächst alle Buchtitel, löscht dann per `CriteriaDelete` alle `Book`-Entities und loggt erneut (Liste ist anschließend leer).

**Gut zu wissen:** Bulk-Deletes umgehen den Persistence Context — bereits geladene Entities und der First-Level-Cache werden nicht automatisch synchronisiert.
