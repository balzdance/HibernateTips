# HibernateJoinUnassociatedEntities

**Kernidee:** Zeigt, wie man in JPQL/HQL zwei Entities joinen kann, die im Mapping gar keine Assoziation zueinander besitzen – über eine explizite Join-Bedingung. Praktisch, wenn nur eine lose Fremdschlüssel-Spalte (`fkBook`) ohne `@ManyToOne` existiert.

**Zentrale Annotation / API:** `JOIN ... ON`-Klausel in JPQL: `FROM Book b INNER JOIN Review r ON r.fkBook = b.id`

**Im Test:** Eine Query zählt pro Buchtitel die Anzahl der Reviews (`GROUP BY b.title`) und liest das Ergebnis als `Object[]`.

**Gut zu wissen:** `Review` hat hier bewusst nur ein einfaches `Long fkBook`-Feld statt einer gemappten Beziehung; der `ON`-Join (ad-hoc, seit JPA 2.1) macht ihn trotzdem joinbar.
