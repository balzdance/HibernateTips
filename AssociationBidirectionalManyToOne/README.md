# AssociationBidirectionalManyToOne

**Kernidee:** Bidirektionale One-to-Many-/Many-to-One-Beziehung zwischen `Book` und `Review`, von beiden Seiten navigierbar.

**Zentrale Annotation / API:** `@ManyToOne` mit `@JoinColumn(name="fk_book")` (Owning Side `Review`) und `@OneToMany(mappedBy="book")` (Inverse Side `Book`).

**Im Test:** Ein neuer `Review` wird gespeichert und mit einem bestehenden `Book` verknüpft; danach wird geprüft, dass `Book` den Review enthält und der Review zurück auf das `Book` zeigt.

**Gut zu wissen:** Die Owning Side ist die Many-Seite (`Review`) mit der Fremdschlüsselspalte; `mappedBy` markiert die Inverse Side. Helfermethode `addReview()` setzt beide Richtungen konsistent.
