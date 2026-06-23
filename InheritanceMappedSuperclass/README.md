# InheritanceMappedSuperclass

**Kernidee:** Zeigt `@MappedSuperclass`: Eine Basisklasse vererbt nur ihre Mapping-Informationen (id, version, Felder) an die Subklassen, ist aber selbst keine Entity. Jede Subklasse erhält ihre eigene, vollständige Tabelle ohne gemeinsame Vererbungs-Semantik.

**Zentrale Annotation / API:** `@MappedSuperclass` auf der abstrakten `Publication`; die konkrete Subklasse `Book` ist die eigentliche `@Entity`.

**Im Test:** Ein `Book` wird persistiert, per JPQL-Query (id=1) wieder geladen und als `Book`-Instanz geprüft.

**Gut zu wissen:** Da `Publication` keine Entity ist, sind keine polymorphen Queries über die Basisklasse möglich und Beziehungen können nicht auf `Publication` zeigen – im Unterschied zu `@Inheritance`-Strategien.
