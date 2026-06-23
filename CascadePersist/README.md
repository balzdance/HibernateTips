# CascadePersist

**Kernidee:** Zeigt, wie das Persistieren einer Entity automatisch an assoziierte Entities weitergereicht wird, sodass nur die Wurzel-Entity explizit gespeichert werden muss.

**Zentrale Annotation / API:** `@ManyToMany(mappedBy="authors", cascade = CascadeType.PERSIST)`, `em.persist(...)`

**Im Test:** Erzeugt einen `Author` mit zwei neuen `Book`-Objekten und ruft nur `em.persist(author)` auf; die Bücher werden per Cascade mitgespeichert.

**Gut zu wissen:** `CascadeType.PERSIST` deckt nur das Persistieren ab, nicht z. B. `merge` oder `remove`. Bei Many-to-Many sollte Cascade bewusst gewählt werden, um nicht versehentlich gemeinsam genutzte Entities zu verändern.
