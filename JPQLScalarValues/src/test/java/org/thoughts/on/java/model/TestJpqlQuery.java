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
	public void scalarValueProjection() {
		log.info("... scalarValueProjection ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Object[]> q = em.createQuery("SELECT b.title, b.publisher.name FROM Book b WHERE b.id = :id", Object[].class);
		q.setParameter("id", 1L);
		Object[] result = q.getSingleResult();

		Assertions.assertTrue(result[0] instanceof String);
		Assertions.assertEquals("Hibernate Tips", result[0]);
		Assertions.assertTrue(result[1] instanceof String);
		log.info(result[0] + " was published by " + result[1]);

		em.getTransaction().commit();
		em.close();
	}
}
