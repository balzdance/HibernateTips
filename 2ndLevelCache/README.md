# 2ndLevelCache

**Kernidee:** Zeigt den Hibernate Second-Level-Cache, der Entities sessionübergreifend zwischenspeichert, sodass wiederholte Zugriffe nicht erneut die Datenbank treffen.

**Zentrale Annotation / API:** `@Cacheable` an der Entity sowie `emf.unwrap(SessionFactory.class).getStatistics()` zur Auswertung.

**Im Test:** Zwei getrennte EntityManager laden denselben `Author`; der erste füllt den Cache (1 Put, 1 Miss), der zweite liest ihn daraus (1 Hit), geprüft über die `Statistics`.

**Gut zu wissen:** Der Cache muss in der `persistence.xml` aktiviert werden (Cache-Mode/Provider); `@Cacheable` allein genügt nicht. Caches leben an der `SessionFactory`, nicht an einer einzelnen Session.
