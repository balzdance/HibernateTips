package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJpqlQuery {

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
	public void pojoProjection() {
		log.info("... pojoProjection ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<BookValue> q = em.createQuery("SELECT new org.thoughts.on.java.model.BookValue(b.id, b.title, b.publisher.name) FROM Book b WHERE b.id = :id", BookValue.class);
		q.setParameter("id", 1L);
		BookValue b = q.getSingleResult();

		Assertions.assertTrue(b instanceof BookValue);
		Assertions.assertEquals(Long.valueOf(1), ((BookValue)b).getId());
		log.info("{}", b);

		em.getTransaction().commit();
		em.close();
	}
}
