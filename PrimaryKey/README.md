# PrimaryKey

**Kernidee:** Zeigt die einfachste Form eines Primärschlüssels: ein `@Id`-Feld, dessen Wert die Anwendung selbst vergibt (kein automatisches Generieren).

**Zentrale Annotation / API:** `@Id` auf dem `Long id`-Feld (ohne `@GeneratedValue`), mit Setter `setId(...)`

**Im Test:** Die `id` wird manuell auf `1L` gesetzt, der `Author` persistiert und geflusht, danach in neuer Session über `find(Author.class, 1L)` wiedergefunden.

**Gut zu wissen:** Ohne `@GeneratedValue` muss der Schlüssel vor dem `persist` gesetzt sein, sonst gibt es eine Null-Verletzung. Dies ist die Basis-Variante; die automatischen Strategien zeigen die anderen PrimaryKey-Module.
