# CriteriaConstructor

**Kernidee:** Zeigt, wie eine Criteria-Query das Ergebnis direkt in ein DTO/POJO projiziert, statt ganze Entities zu laden (Constructor Expression).

**Zentrale Annotation / API:** `CriteriaBuilder.construct(AuthorValue.class, ...)`, `CriteriaQuery<AuthorValue>`, Metamodel `Author_.firstName`

**Im Test:** Selektiert `firstName` und `lastName` aus `Author` und baut daraus pro Zeile ein `AuthorValue`-Objekt über dessen Konstruktor.

**Gut zu wissen:** Das Ziel-POJO (`AuthorValue`) braucht einen passenden Konstruktor mit genau den selektierten Parametern. Das statische Metamodel (`Author_`) wird beim Build per Annotation Processor generiert.
