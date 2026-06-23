# DatabaseViews

**Kernidee:** Zeigt, wie man eine (read-only) Datenbank-Sicht auf eine Entity mappt, ohne eine DB-spezifische `CREATE VIEW`-Anweisung zu benötigen: Die View wird als Subselect direkt in der Entity definiert.

**Zentrale Annotation / API:** `@Subselect(...)` (das SELECT der View) zusammen mit `@Immutable` und `@Synchronize({ "Book", "Author", "BookAuthor" })`

**Im Test:** `selectFromView` liest die `BookView`-Entities (Buchtitel samt zusammengefasster Autoren) per JPQL; `updateView` zeigt, dass eine Änderung an der schreibgeschützten View ignoriert wird.

**Gut zu wissen:** `@Synchronize` nennt die zugrunde liegenden Tabellen, damit Hibernate ausstehende Änderungen vor der Abfrage flusht; die Autoren werden hier über `GROUP_CONCAT` (H2-Syntax) zusammengeführt.
