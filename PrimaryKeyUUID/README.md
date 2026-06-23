# PrimaryKeyUUID

**Kernidee:** Demonstriert UUID-Primärschlüssel mit dem Hibernate-6-Generator. `Author` nutzt eine zufällige UUID (Version 4) per Standard-`@GeneratedValue`, `Book` eine zeitbasierte UUID (Version 1).

**Zentrale Annotation / API:** `@Id @GeneratedValue` mit `UUID`-Feld (V4) bzw. `@UuidGenerator(style = UuidGenerator.Style.TIME)` (V1)

**Im Test:** Persistiert `Author` und `Book`, lädt sie über die generierte UUID neu und prüft, dass die ID übereinstimmt.

**Gut zu wissen:** `@UuidGenerator` ist neu in Hibernate 6 und ersetzt die alten Custom-Generator-Strategien; ohne `style` wird per Default eine zufällige UUID (V4) erzeugt.
