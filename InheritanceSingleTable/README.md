# InheritanceSingleTable

**Kernidee:** Zeigt die JPA-Vererbungsstrategie SINGLE_TABLE: Eine Klassenhierarchie (`Publication` -> `Book`, `BlogPost`) wird in einer einzigen Datenbanktabelle abgebildet. Vorteil: keine Joins, sehr performant.

**Zentrale Annotation / API:** `@Inheritance(strategy = InheritanceType.SINGLE_TABLE)` auf der abstrakten Basisklasse, `@DiscriminatorValue("Book")` auf den Unterklassen.

**Im Test:** Persistiert ein `Book`, lädt es per JPQL wieder als `Book` und greift über die `Author`-Assoziation auf die `Publication`-Liste zu.

**Gut zu wissen:** Alle Felder der Unterklassen landen in derselben Tabelle und müssen daher nullable sein; eine Diskriminator-Spalte unterscheidet die konkreten Typen.
