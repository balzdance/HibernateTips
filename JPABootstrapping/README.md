# JPABootstrapping

**Kernidee:** Zeigt das Standard-Bootstrapping einer JPA-Anwendung: Aufbau einer `EntityManagerFactory` aus der Persistence Unit und Erzeugen eines `EntityManager` für den Datenbankzugriff.

**Zentrale Annotation / API:** `Persistence.createEntityManagerFactory("my-persistence-unit")`, `emf.createEntityManager()`, Konfiguration über `META-INF/persistence.xml`.

**Im Test:** Baut die `EntityManagerFactory` auf, startet eine Transaktion, lädt ein `Book` per `em.find(Book.class, 1L)` und schließt alles wieder sauber.

**Gut zu wissen:** Die `persistence.xml` nutzt H2 In-Memory, `drop-and-create` und ein `data.sql`-Ladeskript; die `EntityManagerFactory` ist teuer und sollte pro Anwendung nur einmal erzeugt werden.
