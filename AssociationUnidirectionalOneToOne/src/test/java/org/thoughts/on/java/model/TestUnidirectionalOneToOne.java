package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUnidirectionalOneToOne {

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
	public void unidirectionalOneToOne() {
		log.info("... unidirectionalOneToOne ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);

		Manuscript m = new Manuscript();
		m.setBook(b);

		em.persist(m);

		em.getTransaction().commit();
		em.close();

		// Get Book entity with Authors
		em = emf.createEntityManager();
		em.getTransaction().begin();

		m = em.find(Manuscript.class, 1L);
		Assertions.assertEquals(b, m.getBook());

		em.getTransaction().commit();
		em.close();
	}
}
