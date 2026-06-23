# AttributeConverterForDateAndTime

**Kernidee:** Zeigt, wie ein JPA `AttributeConverter` einen Java-Typ (`LocalDate`) automatisch in einen Datenbanktyp (`java.sql.Date`) und zurück konvertiert.

**Zentrale Annotation / API:** `@Converter(autoApply = true)`, `AttributeConverter<LocalDate, Date>` mit `convertToDatabaseColumn` / `convertToEntityAttribute`

**Im Test:** Persistiert einen `Author` mit `dateOfBirth` und lädt ihn anschließend neu; der Konverter wird bei beiden Richtungen automatisch aufgerufen.

**Gut zu wissen:** Durch `autoApply = true` greift der Konverter ohne explizite `@Convert`-Annotation auf alle `LocalDate`-Felder. Mit JPA 2.2 / Hibernate ist `LocalDate` allerdings auch nativ unterstützt — das Beispiel demonstriert das Prinzip.
