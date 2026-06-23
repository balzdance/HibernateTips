# NamedNativeQuery

**Kernidee:** Zeigt, wie eine native SQL-Abfrage benannt und am Entity vordefiniert wird, sodass sie zentral verwaltet und per Name wiederverwendet werden kann.

**Zentrale Annotation / API:** `@NamedNativeQuery(name=..., query=..., resultClass=Book.class)`; Aufruf über `em.createNamedQuery(...)`

**Im Test:** Die benannte Query `Book.selectById` wird mit Positionsparameter (id=100) ausgeführt und liefert das passende `Book`-Entity zurück.

**Gut zu wissen:** Der Query-Name wird hier als Konstante (`Book.QUERY_SELECT_BY_ID`) gehalten, um Tippfehler zu vermeiden. Trotz nativem SQL wird über `resultClass` ein verwaltetes Entity zurückgegeben.
