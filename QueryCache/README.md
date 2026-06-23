# QueryCache

**Kernidee:** Zeigt den Query-Cache, der Ergebnisse einer Query (die IDs) über Sessions hinweg zwischenspeichert, sodass identische Abfragen nicht erneut an die Datenbank gehen.

**Zentrale Annotation / API:** `Query.setCacheable(true)` (JPA-Äquivalent: Hint `hibernate.cacheable`); ausgewertet über `Statistics`

**Im Test:** Führt dieselbe Query in zwei Sessions aus und prüft via `Statistics`, dass erst ein `QueryCachePut` und dann ein `QueryCacheHit` entsteht.

**Gut zu wissen:** Der Query-Cache muss zusätzlich aktiviert sein (hier JCache/Ehcache 3) und ist nur sinnvoll zusammen mit dem Second-Level-Cache der Entities.
