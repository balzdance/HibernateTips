# CriteriaStandardFunction

**Kernidee:** Zeigt die Nutzung einer Standard-Funktion der Criteria API, um in einer Query eine Aggregation (Anzahl der Elemente einer Collection) zu berechnen.

**Zentrale Annotation / API:** `cb.size(root.get(Author_.books))` zusammen mit `cb.createTupleQuery()`, `cq.multiselect(...)` und `cq.groupBy(...)`

**Im Test:** Ermittelt pro `Author` (gruppiert nach `Author_.id`) die Anzahl seiner Bücher und gibt die Tupel (Autor, Buchanzahl) aus.

**Gut zu wissen:** `cb.size()` erzeugt ein COUNT auf der Join-Tabelle; deshalb ist hier ein `groupBy` über die Autor-ID nötig.
