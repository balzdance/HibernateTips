# TransientAttributes

**Kernidee:** Zeigt `@Transient`-Attribute, die nicht persistiert werden – etwa abgeleitete/berechnete Werte (hier das Alter aus dem Geburtsdatum) oder Hilfsfelder wie ein Logger.

**Zentrale Annotation / API:** `@Transient` auf Feld (`age`, `log`); berechneter Wert wahlweise on-the-fly oder im transienten Feld gecacht

**Im Test:** Persistiert einen `Author` mit Geburtsdatum und prüft, dass `getAge()`/`getCalculatedAge()` das erwartete Alter liefern, obwohl `age` nicht in der DB steht.

**Gut zu wissen:** `@Transient`-Felder werden weder gemappt noch geladen; ihr Wert wird nach dem Laden zur Laufzeit erst durch den Getter berechnet.
