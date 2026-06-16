package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestQueryCache {

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
	public void selectAuthors() {
		log.info("... selectAuthors ...");

		Statistics stats = emf.unwrap(SessionFactory.class).getStatistics();

		// Session 1 - executes the query and stores its result in the query cache
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Session s = em.unwrap(Session.class);
		Query<Author> q = s.createQuery("SELECT a FROM Author a WHERE id = :id", Author.class);
		q.setParameter("id", 1L);
		q.setCacheable(true);
		log.info("{}", q.getSingleResult());

		em.getTransaction().commit();
		em.close();

		Assertions.assertEquals(1, stats.getQueryCachePutCount());

		// Session 2 - reads the query result from the query cache
		em = emf.createEntityManager();
		em.getTransaction().begin();

		s = em.unwrap(Session.class);
		q = s.createQuery("SELECT a FROM Author a WHERE id = :id", Author.class);
		q.setParameter("id", 1L);
		q.setCacheable(true);
		log.info("{}", q.getSingleResult());

		em.getTransaction().commit();
		em.close();

		Assertions.assertEquals(1, stats.getQueryCacheHitCount());
	}
}
