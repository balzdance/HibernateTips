# LogSQLStatements

**Kernidee:** Zeigt, wie man die von Hibernate generierten SQL-Statements zu Debug-Zwecken sichtbar macht (über Konfiguration, nicht über Code).

**Zentrale Annotation / API:** `hibernate.show_sql` / `hibernate.format_sql` bzw. der Logger `org.hibernate.SQL`

**Im Test:** Führt eine einfache `SELECT a FROM Author a WHERE a.id = :id`-Query aus, deren SQL dann je nach Logging-Konfiguration in der Konsole erscheint.

**Gut zu wissen:** `show_sql` schreibt direkt nach System.out; für Parameterwerte und sauberes Logging nutzt man besser den `org.hibernate.SQL`- (und `BasicBinder`-) Logger.
