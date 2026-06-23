# JPQLScalarValues

**Kernidee:** Zeigt, wie eine JPQL-Query nur einzelne Skalarwerte (Projektion) statt ganzer Entities lädt. Nützlich, wenn man nur wenige Spalten braucht und nicht die komplette Entity holen will.

**Zentrale Annotation / API:** `em.createQuery("SELECT b.title, b.publisher.name FROM Book b ...", Object[].class)`

**Im Test:** Selektiert Titel und Verlagsname eines Buchs und erhält das Ergebnis als `Object[]`, dessen Elemente einzeln geprüft werden.

**Gut zu wissen:** Bei mehreren Projektionsspalten liefert die Query ein `Object[]`; die Reihenfolge der Elemente entspricht der SELECT-Klausel.
