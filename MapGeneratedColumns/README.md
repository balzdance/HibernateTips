# MapGeneratedColumns

**Kernidee:** Zeigt das Mapping einer von der Datenbank erzeugten Spalte (`lastUpdate`): Hibernate liest den Wert nach INSERT/UPDATE automatisch zurück, statt ihn selbst zu setzen.

**Zentrale Annotation / API:** `@Generated(event = { EventType.INSERT, EventType.UPDATE })`

**Im Test:** Legt einen Author an (lastUpdate wird gesetzt), ändert ihn nach kurzer Pause und prüft, dass die neue Update-Zeit nicht der Erstellzeit entspricht.

**Gut zu wissen:** Den eigentlichen Spaltenwert liefert ein DB-Trigger; in diesem Beispiel ersetzt der H2-`SyncLastUpdateTrigger` den ursprünglichen PostgreSQL-Trigger.
