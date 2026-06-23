# MapUtilDate

**Kernidee:** Zeigt, wie ein klassisches `java.util.Date`-Attribut auf eine Datenbankspalte gemappt wird, wobei die gewünschte Granularität (nur Datum) festgelegt wird.

**Zentrale Annotation / API:** `@Temporal(TemporalType.DATE)` auf einem `java.util.Date`-Feld

**Im Test:** Ein `Author` mit gesetztem `dateOfBirth` wird persistiert und anschließend in einer neuen Session wieder geladen und geloggt.

**Gut zu wissen:** `@Temporal` ist nur für `java.util.Date`/`Calendar` nötig; bei den modernen `java.time`-Typen (`LocalDate` usw.) entfällt es. Der Konstruktor `new Date(80, 0, 1)` ist deprecated und dient hier nur als Testdatum.
