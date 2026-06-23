# DerivedPrimaryKey

**Kernidee:** Zeigt einen abgeleiteten Primärschlüssel: Ein Teil des zusammengesetzten Schlüssels einer Entity wird aus der Beziehung zu einer anderen Entity übernommen. Nützlich, wenn die ID einer Child-Entity den Fremdschlüssel zur Parent-Entity enthalten soll.

**Zentrale Annotation / API:** `@EmbeddedId` (auf `ReviewId implements Serializable` mit `@Embeddable`) + `@MapsId("bookId")` auf der `@ManyToOne`-Beziehung

**Im Test:** Ein `Review` wird mit `ReviewId` (nur `userName` gesetzt) plus einem gefundenen `Book` persistiert; Hibernate füllt `bookId` automatisch aus der Beziehung. Danach wird der Review per `find(Review.class, new ReviewId("peter", 1L))` wieder geladen.

**Gut zu wissen:** Die `bookId` darf vor dem Persistieren nicht manuell gesetzt werden, `@MapsId` übernimmt das aus dem zugeordneten `Book`.
