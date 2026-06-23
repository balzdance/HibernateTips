# CriteriaQuery

**Kernidee:** Zeigt eine typsichere Criteria-Query mit Join über eine Assoziation und parametrisierter `where`-Bedingung.

**Zentrale Annotation / API:** `CriteriaBuilder` / `CriteriaQuery<Book>`, `root.join(Book_.authors)` (`SetJoin`), `cb.and(...)`, `cb.equal(...)`, `ParameterExpression`

**Im Test:** Joint `Book` auf seine `authors` und sucht über zwei `String`-Parameter das Buch des Autors "Thorben Janssen"; erwartet genau ein Ergebnis.

**Gut zu wissen:** Die Parameter werden als `ParameterExpression` definiert und erst auf der `TypedQuery` gesetzt. Das statische Metamodel (`Book_`, `Author_`) wird beim Build generiert.
