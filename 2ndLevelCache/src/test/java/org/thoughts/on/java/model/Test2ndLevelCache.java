package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test2ndLevelCache {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private EntityManagerFactory emf;

	@BeforeEach
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
	}

	@AfterEach
	public void close() {
		emf.close();
	}

	@Test
	public void selectAuthor() {
		log.info("... selectAuthor ...");

		Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();

		log.info("Session 1");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, 1L);
		log.info("{}", a);

		em.getTransaction().commit();
		em.close();

		// 1st session loads the entity from the DB and stores it in the 2nd level cache
		Assertions.assertEquals(1, stats.getSecondLevelCachePutCount());
		Assertions.assertEquals(0, stats.getSecondLevelCacheHitCount());

		log.info("Session 2");
		em = emf.createEntityManager();
		em.getTransaction().begin();

		a = em.find(Author.class, 1L);
		log.info("{}", a);

		em.getTransaction().commit();
		em.close();

		// 2nd session reads the entity from the 2nd level cache (1 hit).
		// The 1st session produced exactly 1 miss (cache empty) before the put.
		Assertions.assertEquals(1, stats.getSecondLevelCacheHitCount());
		Assertions.assertEquals(1, stats.getSecondLevelCacheMissCount());
	}
}
