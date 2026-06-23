# JoinFetch

**Kernidee:** Zeigt `JOIN FETCH`, um eine Lazy-Beziehung direkt mit der Query zu initialisieren und so `LazyInitializationException` bzw. das N+1-Problem zu vermeiden.

**Zentrale Annotation / API:** `SELECT a FROM Author a JOIN FETCH a.books WHERE a.id = 1`

**Im Test:** Ein Test lädt den Author ohne Fetch und greift nach geschlossener Session auf `a.getBooks()` zu (erwartet `LazyInitializationException`); der zweite Test nutzt `JOIN FETCH` und kann die Bücher danach problemlos lesen.

**Gut zu wissen:** Ohne aktive Session/Transaktion schlägt der Zugriff auf eine nicht initialisierte Lazy-Collection fehl.
