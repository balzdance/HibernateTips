# JPQLConstructorExpression

**Kernidee:** Zeigt die JPQL-Constructor-Expression, mit der eine Query direkt in ein DTO/POJO (`BookValue`) projiziert wird, statt ganze Entitäten zu laden.

**Zentrale Annotation / API:** `SELECT new org.thoughts.on.java.model.BookValue(b.id, b.title, b.publisher.name) FROM Book b` – voll qualifizierter Klassenname plus passender Konstruktor im DTO.

**Im Test:** Führt die Constructor-Expression-Query aus und erhält ein `BookValue`-Objekt mit `id`, `title` und `publisherName`.

**Gut zu wissen:** Das DTO muss einen Konstruktor besitzen, dessen Parameterreihenfolge und -typen exakt zur SELECT-Liste passen; der Klassenname muss vollständig qualifiziert angegeben werden.
