# AccessHibernateApi

**Kernidee:** Zeigt, wie man aus der standardisierten JPA-API heraus an die proprietäre Hibernate-API gelangt, um Hibernate-spezifische Funktionen zu nutzen.

**Zentrale Annotation / API:** `em.unwrap(Session.class)` und `em.getEntityManagerFactory().unwrap(SessionFactory.class)`.

**Im Test:** Ein Test holt aus dem `EntityManager` die Hibernate-`Session`, ein zweiter aus der `EntityManagerFactory` die `SessionFactory`.

**Gut zu wissen:** `unwrap` ist der saubere Weg, von JPA zu Hibernate herabzusteigen, ohne den Code vollständig an Hibernate zu binden.
