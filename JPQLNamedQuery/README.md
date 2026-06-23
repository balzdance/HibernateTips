# JPQLNamedQuery

**Kernidee:** Zeigt vordefinierte, benannte JPQL-Abfragen, die einmal an der Entität deklariert und überall per Namen wiederverwendet werden – beim Start validiert und gut zentralisierbar.

**Zentrale Annotation / API:** `@NamedQuery(name = ..., query = "SELECT b FROM Book b WHERE b.id = :id")` (jakarta) auf der `Book`-Entität, aufgerufen via `em.createNamedQuery(Book.QUERY_SELECT_BY_ID, Book.class)`.

**Im Test:** Ruft die Named Query über die Konstante `Book.QUERY_SELECT_BY_ID` auf, setzt den Parameter und liest genau ein `Book`.

**Gut zu wissen:** Query- und Parameternamen werden als `public static final`-Konstanten in der Entität gehalten; Named Queries werden bereits beim Aufbau der `EntityManagerFactory` geparst, Syntaxfehler fallen also früh auf.
