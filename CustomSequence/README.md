# CustomSequence

**Kernidee:** Zeigt, wie man Primärschlüssel über eine eigene Datenbank-Sequenz mit selbst gewähltem Namen generiert.

**Zentrale Annotation / API:** `@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")` plus `@SequenceGenerator(name = "author_generator", sequenceName = "author_seq")`

**Im Test:** Persistiert einen neuen `Author`; Hibernate holt den ID-Wert aus der Sequenz `author_seq`.

**Gut zu wissen:** Der `name` im `@SequenceGenerator` verknüpft nur mit dem `generator`-Verweis, während `sequenceName` den tatsächlichen DB-Sequenznamen festlegt.
