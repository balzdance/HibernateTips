# AssociationUnidirectionalOneToOne

**Kernidee:** Unidirektionale One-to-One-Beziehung von `Manuscript` zu `Book`, nur von `Manuscript` aus navigierbar.

**Zentrale Annotation / API:** `@OneToOne` mit `@JoinColumn(name="fk_book")` auf der `Manuscript`-Seite; `Book` enthält keine Gegenrichtung.

**Im Test:** Ein neues `Manuscript` wird mit einem bestehenden `Book` verknüpft und gespeichert; danach wird über `manuscript.getBook()` die Navigation geprüft.

**Gut zu wissen:** Der Fremdschlüssel liegt auf der Seite mit der `@JoinColumn` (`Manuscript`). `Book` weiß nichts von seinem `Manuscript`, da die Beziehung unidirektional ist.
