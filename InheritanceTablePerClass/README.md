# InheritanceTablePerClass

**Kernidee:** Zeigt die JPA-Vererbungsstrategie TABLE_PER_CLASS: Jede konkrete Klasse (`Publication`, `Book`, `BlogPost`) bekommt eine eigene, vollständige Tabelle inklusive aller geerbten Spalten.

**Zentrale Annotation / API:** `@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)` auf der Basisklasse (hier keine Diskriminator-Spalte nötig).

**Im Test:** Persistiert ein `Book`, lädt es per JPQL wieder und iteriert polymorph über die `Publication`-Assoziation des `Author`.

**Gut zu wissen:** Polymorphe Abfragen über die Basisklasse erzeugen ein `UNION` über alle Tabellen; `GenerationType.AUTO`/Identity-IDs sind hier problematisch, daher wird ein tabellenübergreifender Sequenz-/Generator-Mechanismus benötigt.
