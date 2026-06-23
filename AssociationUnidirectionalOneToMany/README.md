# AssociationUnidirectionalOneToMany

**Kernidee:** Unidirektionale One-to-Many-Beziehung von `Book` zu `Review`, nur von `Book` aus navigierbar; `Review` kennt sein `Book` nicht.

**Zentrale Annotation / API:** `@OneToMany` mit `@JoinColumn(name="fk_book")` auf der `Book`-Seite (statt `mappedBy`).

**Im Test:** Ein neuer `Review` wird zur Liste eines bestehenden `Book` hinzugefügt und persistiert; danach wird geprüft, dass `book.getReviews()` den Review enthält.

**Gut zu wissen:** Ohne `mappedBy` verwaltet Hibernate den Fremdschlüssel über `@JoinColumn`. Diese Variante gilt als ineffizient (zusätzliche UPDATE-Statements) – eine bidirektionale Many-to-One-Beziehung ist meist vorzuziehen.
