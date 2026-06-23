# MapEnumerations

**Kernidee:** Zeigt, wie ein Java-Enum als Entity-Attribut persistiert wird, hier als lesbarer String statt als Ordinalzahl.

**Zentrale Annotation / API:** `@Enumerated(EnumType.STRING)`

**Im Test:** Persistiert einen Author mit `status = AuthorStatus.PUBLISHED` und lädt ihn anschließend erneut.

**Gut zu wissen:** `EnumType.STRING` ist robuster als das Default `EnumType.ORDINAL`: Wird die Reihenfolge der Enum-Konstanten geändert, bleiben bereits gespeicherte Werte korrekt.
