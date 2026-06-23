# CustomEnumerationsMapping

**Kernidee:** Zeigt, wie man ein Java-Enum mit einem JPA `AttributeConverter` auf einen frei gewählten Datenbankwert mappt (hier `AuthorStatus` -> "N"/"P"/"S"), statt auf den ORDINAL- oder STRING-Standard angewiesen zu sein.

**Zentrale Annotation / API:** `@Converter(autoApply = true)` auf einer Klasse, die `AttributeConverter<AuthorStatus, String>` mit `convertToDatabaseColumn` / `convertToEntityAttribute` implementiert

**Im Test:** Persistiert einen `Author` mit Status `PUBLISHED` und lädt ihn in einer zweiten Transaktion erneut; das Logging zeigt die Konvertierung in beide Richtungen.

**Gut zu wissen:** Trotz des Modulnamens kommt kein Hibernate-`UserType` zum Einsatz, sondern ein standardisierter JPA-Converter; durch `autoApply = true` wird er ohne `@Convert` am Feld automatisch angewendet.
