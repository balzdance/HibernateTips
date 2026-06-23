# MapBasicAttributes

**Kernidee:** Zeigt das Standard-Mapping einfacher Entity-Attribute auf Tabellenspalten, inklusive Anpassung des Spaltennamens.

**Zentrale Annotation / API:** `@Entity`, `@Id`, `@GeneratedValue`, `@Column(name = "lname")`, `@Version`

**Im Test:** Persistiert einen neuen Author und lädt ihn in einer zweiten Session erneut per `em.find(...)`.

**Gut zu wissen:** Ohne `@Column` wird der Feldname als Spaltenname genutzt; `@Column(name = "lname")` bildet `lastName` explizit auf die Spalte `lname` ab.
