# PrePersistLifecycleEvent

**Kernidee:** Zeigt, wie ein JPA-Lifecycle-Callback genutzt wird, um vor dem ersten Speichern automatisch ein Feld zu befüllen (hier ein `createdAt`-Zeitstempel).

**Zentrale Annotation / API:** `@PrePersist` auf einer Entity-Methode (`initializeCreatedAt()`)

**Im Test:** Ein neuer `Author` wird per `em.persist(a)` gespeichert; die annotierte Methode setzt dabei automatisch `createdAt = LocalDateTime.now()`.

**Gut zu wissen:** `@PrePersist` feuert nur beim erstmaligen Persistieren (für Updates gäbe es `@PreUpdate`). Der `Logger` ist mit `@Transient` markiert, damit Hibernate ihn nicht als persistentes Feld behandelt.
