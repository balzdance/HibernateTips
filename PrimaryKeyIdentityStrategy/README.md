# PrimaryKeyIdentityStrategy

**Kernidee:** Zeigt, wie der Primärschlüssel von einer Auto-Increment-Spalte der Datenbank erzeugt wird, statt von Hibernate oder der Anwendung.

**Zentrale Annotation / API:** `@Id` mit `@GeneratedValue(strategy = GenerationType.IDENTITY)`

**Im Test:** Ein `Author` ohne gesetzte id wird persistiert; die id wird erst beim `persist`/Insert von der DB vergeben (sichtbar an den Logs "Before/After persist").

**Gut zu wissen:** Bei `IDENTITY` muss Hibernate sofort beim `persist` ein INSERT absetzen, um die ID zu erhalten – das verhindert das JDBC-Batching von Inserts. Bei H2 wird dafür eine `IDENTITY`/Auto-Increment-Spalte verwendet.
