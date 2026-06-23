# HibernateBootstrapping

**Kernidee:** Zeigt das programmatische Bootstrapping einer Hibernate `SessionFactory` über die native Hibernate-API, statt über JPA `Persistence.createEntityManagerFactory(...)`.

**Zentrale Annotation / API:** `new StandardServiceRegistryBuilder().configure().build()` → `new MetadataSources(registry).addAnnotatedClass(Author.class).buildMetadata().buildSessionFactory()`; danach `Session`-API.

**Im Test:** Baut Registry, Metadata und `SessionFactory` auf, öffnet eine `Session`, persistiert einen `Author` in einer Transaktion und schließt alles wieder.

**Gut zu wissen:** `.configure()` lädt standardmäßig die `hibernate.cfg.xml`; annotierte Klassen müssen explizit per `addAnnotatedClass(...)` registriert werden.
