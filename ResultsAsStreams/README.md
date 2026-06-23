# ResultsAsStreams

**Kernidee:** Zeigt, wie Query-Ergebnisse als Java-`Stream` statt als `List` abgerufen werden können, um sie direkt mit der Stream-API zu verarbeiten.

**Zentrale Annotation / API:** `Query.getResultStream()` (gegenüber `getResultList()`)

**Im Test:** Liest alle `Book`-Entities einmal als `List` (`.stream()`) und einmal direkt als `Stream` über `getResultStream()` und mappt sie auf Log-Ausgaben.

**Gut zu wissen:** `getResultStream()` kann (bei passendem Treiber/Fetch-Konfiguration) ergebnisweise lesen; der Stream sollte innerhalb der offenen Session/Transaktion verarbeitet werden.
