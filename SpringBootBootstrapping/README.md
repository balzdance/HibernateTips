# SpringBootBootstrapping

**Kernidee:** Zeigt das Aufsetzen von JPA/Hibernate mit Spring Boot 3 und Spring Data JPA, sodass `EntityManagerFactory`, Transaktionen und Datenquelle automatisch konfiguriert werden.

**Zentrale Annotation / API:** `@SpringBootApplication` plus `SpringApplication.run(...)`; injizierter `EntityManager` per `@Autowired`

**Im Test:** Ein `@SpringBootTest` injiziert den `EntityManager` und persistiert in einer `@Transactional @Commit`-Methode einen `Author`.

**Gut zu wissen:** Spring Boot übernimmt das Bootstrapping ohne `persistence.xml`; `@Commit` ist nötig, weil Spring-Test-Transaktionen standardmäßig zurückgerollt werden.
