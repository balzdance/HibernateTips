# AssociationsWithAttributes

**Kernidee:** Zeigt, wie man eine Many-to-Many-Beziehung mit zusätzlichen Attributen (hier `format`) als eigene Assoziations-Entity modelliert. Die Verknüpfungstabelle wird so zu einer vollwertigen Entity.

**Zentrale Annotation / API:** `@EmbeddedId` mit `@Embeddable`-Id-Klasse, `@MapsId` auf den beiden `@ManyToOne`-Beziehungen, `@JoinColumn`

**Im Test:** Lädt ein `Book` und einen `Publisher`, erzeugt eine `BookPublisher`-Entity mit `Format.PAPERBACK` und persistiert sie.

**Gut zu wissen:** Der zusammengesetzte Schlüssel (`bookId` + `publisherId`) liegt im `@Embeddable BookPublisherId`; `@MapsId("bookId")`/`@MapsId("publisherId")` verbinden die Id-Felder mit den jeweiligen `@ManyToOne`-Assoziationen.
