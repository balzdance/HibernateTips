package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestNamedNativeQuery {

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
	public void namedNativeQuery() {
		log.info("... namedNativeQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createNamedQuery(Book.QUERY_SELECT_BY_ID);
		q.setParameter(1, 100);
		Book b = (Book) q.getSingleResult();
		Assertions.assertTrue(b instanceof Book);
		Assertions.assertEquals(Long.valueOf(100), ((Book) b).getId());

		em.getTransaction().commit();
		em.close();
	}
}
