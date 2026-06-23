# AssociationBidirectionalManyToMany

**Kernidee:** Bidirektionale Many-to-Many-Beziehung zwischen `Book` und `Author`, von beiden Seiten navigierbar und über eine Join-Tabelle abgebildet.

**Zentrale Annotation / API:** `@ManyToMany` mit `@JoinTable` (Owning Side `Book`) und `@ManyToMany(mappedBy="authors")` (Inverse Side `Author`).

**Im Test:** Ein neuer `Author` wird mit einem bestehenden `Book` verknüpft – einmal manuell auf beiden Seiten, einmal über die Helfermethode `addBook()` – und die Beziehung danach in einer neuen Session geprüft.

**Gut zu wissen:** Beide Listen müssen synchron gehalten werden; Helfermethoden (`addBook`/`addAuthor`) verhindern Inkonsistenzen. Nur die Owning Side (`Book`) schreibt die Join-Tabelle.
