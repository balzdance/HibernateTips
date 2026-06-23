# EntityGraph

**Kernidee:** Zeigt EntityGraphs, um pro Query festzulegen, welche Assoziationen eager geladen werden. So lässt sich N+1-Problemen begegnen, ohne das statische Fetch-Mapping der Entity zu ändern.

**Zentrale Annotation / API:** Dynamisch via `em.createEntityGraph(Author.class)` + `graph.addAttributeNodes(...)`; benannt via `@NamedEntityGraph(name = "graph.AuthorBooks")` + `em.createEntityGraph("graph.AuthorBooks")`. Aktiviert per Query-Hint `jakarta.persistence.fetchgraph`.

**Im Test:** Zwei Tests laden denselben `Author` (id=1) inklusive seiner `books`-Sammlung – einmal mit dynamischem, einmal mit benanntem Graph.

**Gut zu wissen:** Bei `fetchgraph` werden nur die im Graph genannten Attribute eager geladen; alle übrigen bleiben lazy (im Gegensatz zu `loadgraph`).
