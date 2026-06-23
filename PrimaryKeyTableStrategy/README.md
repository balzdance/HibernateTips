# PrimaryKeyTableStrategy

**Kernidee:** Zeigt die Primärschlüssel-Generierung über eine separate Datenbanktabelle (statt Sequence oder Identity). Nützlich für Datenbanken ohne Sequence-Unterstützung, gilt aber als performance-schwächste Strategie.

**Zentrale Annotation / API:** `@GeneratedValue(strategy = GenerationType.TABLE)` auf `@Id`

**Im Test:** Persistiert einen `Author` und committet; Hibernate zieht den nächsten Wert aus der Generator-Tabelle.

**Gut zu wissen:** `GenerationType.TABLE` benötigt eigene Zugriffe auf die Hilfstabelle und ist langsamer als Sequences; in der Praxis meist nur als Fallback einzusetzen.
