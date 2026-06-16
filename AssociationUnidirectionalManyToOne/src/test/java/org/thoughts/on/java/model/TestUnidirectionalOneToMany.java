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

public class TestUnidirectionalOneToMany {

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
	public void unidirectionalOneToMany() {
		log.info("... bidirectionalOneToMany ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);

		Review r = new Review();
		r.setComment("This is a comment");
		r.setBook(b);

		em.persist(r);

		em.getTransaction().commit();
		em.close();

		// Get Book entity with Reviews
		em = emf.createEntityManager();
		em.getTransaction().begin();

		r = em.find(Review.class, 1L);

		b = r.getBook();
		Assertions.assertEquals(Long.valueOf(1), b.getId());

		em.getTransaction().commit();
		em.close();
	}
}
