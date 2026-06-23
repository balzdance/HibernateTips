# Treat

**Kernidee:** Zeigt den `TREAT`-Operator, mit dem man in einer Vererbungshierarchie auf einen Subtyp downcastet, um in der Query auf dessen spezifische Attribute zugreifen zu können.

**Zentrale Annotation / API:** JPQL-Operator `treat(p AS Book)`; Hierarchie über `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` (`Publication` → `Book`/`BlogPost`)

**Im Test:** Selektiert Autoren mit ihren Publikationen und filtert über `treat(p AS Book).title LIKE '%Java%'` nur die `Book`-Subtypen.

**Gut zu wissen:** `TREAT` schränkt das Ergebnis implizit auf den Zieltyp ein; nicht passende Subtypen (z. B. `BlogPost`) fallen aus dem Ergebnis heraus.
