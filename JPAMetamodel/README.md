# JPAMetamodel

**Kernidee:** Zeigt die Verwendung des generierten, statischen Metamodells (`Book_`, `Author_`, `Publisher_`) für typsichere Criteria-Queries statt fehleranfälliger String-Attributnamen.

**Zentrale Annotation / API:** Von `jpamodelgen` generierte `Entity_`-Klassen (z. B. `Book_.title`, `Book_.publishingDate`) zusammen mit `CriteriaBuilder` / `CriteriaQuery`.

**Im Test:** Baut eine `CriteriaQuery<Tuple>`, selektiert via `multiselect(root.get(Book_.title), root.get(Book_.publishingDate))` und gibt die Treffer aus.

**Gut zu wissen:** Die `Entity_`-Klassen entstehen erst beim Kompilieren durch den Annotation Processor `hibernate-jpamodelgen`; ohne diesen Generierungsschritt lässt sich der Code nicht übersetzen.
