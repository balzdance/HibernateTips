# MapOptionalAssociations

**Kernidee:** Zeigt, wie eine optionale `@ManyToOne`-Beziehung im Entity über einen `Optional<Publisher>`-Getter sauber abgebildet wird, sodass fehlende Verknüpfungen explizit behandelt werden.

**Zentrale Annotation / API:** `@ManyToOne` mit `@JoinColumn`; Getter liefert `Optional.ofNullable(this.publisher)`

**Im Test:** Es werden zwei Bücher geladen; bei Buch 1 ist `getPublisher().isPresent()` true, bei Buch 2 false (kein Publisher gesetzt).

**Gut zu wissen:** Das Feld selbst bleibt ein normales `Publisher`-Attribut; nur der Getter kapselt mit `Optional`. Niemals `Optional` als Feldtyp verwenden, das kann Hibernate nicht persistieren.
