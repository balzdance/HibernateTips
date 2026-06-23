# JpqlStandardFunction

**Kernidee:** Zeigt den Einsatz einer JPQL-Standardfunktion (`size()`), um die Anzahl der Elemente einer Beziehung direkt in der Query zu ermitteln, ohne die Collection zu laden.

**Zentrale Annotation / API:** `SELECT a, size(a.books) FROM Author a GROUP BY a.id`

**Im Test:** Selektiert jeden Author samt Anzahl seiner Bücher und loggt das Ergebnis aus dem zurückgegebenen `Object[]`.

**Gut zu wissen:** `size()` wird zu einer COUNT-Aggregation übersetzt und benötigt daher ein passendes `GROUP BY`.
