# OrderRelationships

**Kernidee:** Zeigt, wie die Elemente einer to-many-Assoziation beim Laden automatisch sortiert werden, ohne dass im Code manuell sortiert werden muss.

**Zentrale Annotation / API:** `@OrderBy(value = "lastName")` auf der `@ManyToMany`-Beziehung `authors`

**Im Test:** Buch 2 wird geladen (einmal per `find`, einmal per `JOIN FETCH`); seine Autoren erscheinen alphabetisch nach `lastName` sortiert (Bauer, Gregory, King).

**Gut zu wissen:** `@OrderBy` sortiert per `ORDER BY` in SQL anhand einer Entity-Eigenschaft (Standard aufsteigend) – im Gegensatz zu `@OrderColumn`, das eine eigene Positions-Spalte in der DB verwaltet. Hier wird ein `Set` genutzt, die Sortierung kommt also von der Query.
