package org.thoughts.on.java.model;

import java.util.List;

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
		log.info("... unidirectionalOneToMany ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);

		Review r = new Review();
		r.setComment("This is a comment");

		b.getReviews().add(r);

		em.persist(r);

		em.getTransaction().commit();
		em.close();

		// Get Book entity with Reviews
		em = emf.createEntityManager();
		em.getTransaction().begin();

		b = em.find(Book.class, 1L);

		List<Review> reviews = b.getReviews();
		Assertions.assertTrue(reviews.contains(r));

		em.getTransaction().commit();
		em.close();
	}
}
