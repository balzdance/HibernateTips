# CriteriaTuples

**Kernidee:** Zeigt, wie man mit der Criteria API gezielt einzelne Spalten statt ganzer Entities selektiert und das Ergebnis als `Tuple` (Spaltenprojektion) erhält.

**Zentrale Annotation / API:** `cb.createTupleQuery()`, `q.multiselect(...)` mit `.alias(...)` und Zugriff über `tuple.get("alias")`

**Im Test:** Selektiert `firstName` und `lastName` aller `Author`-Entities als `Tuple` und liest die Werte über die vergebenen Aliase aus.

**Gut zu wissen:** Über `alias(...)` lassen sich die Tupel-Werte namentlich statt nur über den Index ansprechen.
