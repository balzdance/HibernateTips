# DefineTableAndSchemaName

**Kernidee:** Zeigt, wie man für eine Entity den Tabellennamen und das Datenbank-Schema explizit festlegt, statt sich auf den abgeleiteten Standardnamen zu verlassen.

**Zentrale Annotation / API:** `@Table(name = "author", schema = "bookstore")`

**Im Test:** Persistiert einen `Author` und liest ihn per JPQL wieder ein; die Daten landen in Tabelle `bookstore.author`.

**Gut zu wissen:** Der `name` der JPQL-Query bleibt der Entity-Name (`Author`); der konfigurierte Tabellen-/Schemaname wirkt sich nur auf das generierte SQL aus.
