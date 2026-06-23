# HibernateNaturalId

**Kernidee:** Zeigt natürliche Schlüssel: ein fachlich eindeutiges Attribut (hier `isbn`) wird als Natural ID markiert und kann über eine eigene, optimierte API geladen werden – inkl. Natural-ID-Cache.

**Zentrale Annotation / API:** `@org.hibernate.annotations.NaturalId` auf dem Feld; geladen via `session.byNaturalId(Book.class).using("isbn", ...).load()` bzw. `session.bySimpleNaturalId(Book.class).load(...)`.

**Im Test:** Ein `Book` wird zweimal über die ISBN `"123-4567890123"` geladen (allgemeine und Simple-Variante) und es wird geprüft, dass dabei das Buch mit id=1 zurückkommt.

**Gut zu wissen:** `bySimpleNaturalId` funktioniert nur bei genau einem `@NaturalId`-Attribut; bei mehreren muss `byNaturalId().using(...)` verwendet werden.
