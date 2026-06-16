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

public class TestNativeQuery {

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
	public void adHocNativeQuery() {
		log.info("... adHocNativeQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createNativeQuery("SELECT * FROM book b WHERE id = ?", Book.class);
		q.setParameter(1, 1);
		Book b = (Book) q.getSingleResult();
		Assertions.assertTrue(b instanceof Book);
		Assertions.assertEquals(Long.valueOf(1), b.getId());

		em.getTransaction().commit();
		em.close();
	}
}
