# InheritanceJoined

**Kernidee:** Zeigt die JOINED-Vererbungsstrategie: Jede Klasse der Hierarchie bekommt eine eigene Tabelle, Subklassen-Tabellen verweisen per Fremdschlüssel auf die Tabelle der Basisklasse. Normalisiert, aber Lesen erfordert Joins.

**Zentrale Annotation / API:** `@Inheritance(strategy = InheritanceType.JOINED)` auf der abstrakten Basis-`@Entity` `Publication`; Subklassen (`Book`, `BlogPost`) sind eigene `@Entity`.

**Im Test:** Ein `Book` wird persistiert und einem `Author` zugeordnet; danach per Query geladen und geprüft, dass es eine `Book`-Instanz ist. Schließlich werden die `publications` des Authors (Basistyp) iteriert.

**Gut zu wissen:** Die `@ManyToMany`-Beziehung zu `Author` liegt auf der Basisklasse `Publication`, sodass die Assoziation polymorph für alle Subtypen gilt.
