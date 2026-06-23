# AssociationUnidirectionalManyToMany

**Kernidee:** Unidirektionale Many-to-Many-Beziehung von `Book` zu `Author`, nur von `Book` aus navigierbar, über eine Join-Tabelle abgebildet.

**Zentrale Annotation / API:** `@ManyToMany` mit `@JoinTable(name="book_author", joinColumns=..., inverseJoinColumns=...)` nur auf der `Book`-Seite.

**Im Test:** Ein neuer `Author` wird zur Autorenliste eines bestehenden `Book` hinzugefügt und persistiert; danach wird geprüft, dass die Liste den Autor enthält.

**Gut zu wissen:** `Author` kennt seine Bücher nicht (kein `mappedBy`), da die Beziehung unidirektional ist. Nur `Book` pflegt die Join-Tabelle.
