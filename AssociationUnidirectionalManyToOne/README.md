# AssociationUnidirectionalManyToOne

**Kernidee:** Unidirektionale Many-to-One-Beziehung von `Review` zu `Book`, nur von `Review` aus navigierbar.

**Zentrale Annotation / API:** `@ManyToOne` mit `@JoinColumn(name="fk_book")` auf der `Review`-Seite; `Book` enthält keine Gegenrichtung.

**Im Test:** Ein neuer `Review` wird mit einem bestehenden `Book` verknüpft und gespeichert; danach wird über `review.getBook().getId()` geprüft, dass der Fremdschlüssel korrekt gesetzt ist.

**Gut zu wissen:** Dies ist die einfachste und effizienteste Form – die Fremdschlüsselspalte liegt direkt auf der Many-Seite (`Review`), ohne Rückverweis von `Book`.
