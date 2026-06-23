# Formula

**Kernidee:** Zeigt berechnete, nicht-persistente Attribute: Der Wert wird beim Lesen per SQL-Ausdruck aus anderen Spalten ermittelt, statt in einer eigenen Spalte gespeichert zu werden.

**Zentrale Annotation / API:** `@org.hibernate.annotations.Formula(value = "datediff('year', dateOfBirth, DATE '2016-05-01')")` auf dem Feld `age`

**Im Test:** Ein `Author` wird per `find` und per JPQL-Query geladen; beide Male liefert `getAge()` den berechneten Wert 43.

**Gut zu wissen:** Der SQL-Ausdruck ist datenbankspezifisch – hier auf H2-Syntax (`datediff('year', ...)`) umgeschrieben und gegen ein festes Referenzdatum gerechnet, damit das Ergebnis deterministisch bleibt.
