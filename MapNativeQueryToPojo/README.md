# MapNativeQueryToPojo

**Kernidee:** Zeigt, wie sich das Ergebnis einer nativen SQL-Abfrage auf eine beliebige POJO-Klasse (kein Entity) mappen lässt, indem die ausgewählten Spalten an einen Konstruktor übergeben werden.

**Zentrale Annotation / API:** `@SqlResultSetMapping` mit `@ConstructorResult` und `@ColumnResult`; `em.createNativeQuery(sql, "BookValueMapping")`

**Im Test:** Eine native Query selektiert Titel und Datum aus der `book`-Tabelle und liefert per `getSingleResult()` ein fertig konstruiertes `BookValue`-Objekt zurück.

**Gut zu wissen:** Die `@ColumnResult`-Namen müssen exakt den Spalten-Aliassen im SQL entsprechen (`b.publishingDate as date`), und die Reihenfolge muss zur Konstruktorsignatur von `BookValue` passen.
