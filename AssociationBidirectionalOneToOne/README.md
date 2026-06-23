# AssociationBidirectionalOneToOne

**Kernidee:** Bidirektionale One-to-One-Beziehung zwischen `Book` und `Manuscript`, von beiden Seiten navigierbar.

**Zentrale Annotation / API:** `@OneToOne` mit `@JoinColumn(name="fk_book")` (Owning Side `Manuscript`) und `@OneToOne(mappedBy="book")` (Inverse Side `Book`).

**Im Test:** Ein neues `Manuscript` wird mit einem bestehenden `Book` verknüpft und gespeichert; danach wird in neuer Session über `book.getManuscript().getBook()` die wechselseitige Navigation geprüft.

**Gut zu wissen:** Der Fremdschlüssel liegt auf der Owning Side (`Manuscript`); `mappedBy` verhindert eine zweite, redundante FK-Spalte auf `Book`.
