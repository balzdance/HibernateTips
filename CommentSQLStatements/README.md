# CommentSQLStatements

**Kernidee:** Zeigt, wie man generierten SQL-Statements einen eigenen Kommentar mitgibt, um sie in Logs und im DB-Monitoring leichter zuordnen zu können.

**Zentrale Annotation / API:** Query-Hint `q.setHint("org.hibernate.comment", "...")`, Property `hibernate.use_sql_comments`

**Im Test:** Setzt denselben Kommentar-Hint auf eine JPQL-, eine Native- und eine Criteria-Query und führt diese aus; der Kommentar erscheint im geloggten SQL.

**Gut zu wissen:** Damit Kommentare im SQL erscheinen, muss `hibernate.use_sql_comments=true` gesetzt sein (in der `persistence.xml` zusammen mit `show_sql`/`format_sql`).
