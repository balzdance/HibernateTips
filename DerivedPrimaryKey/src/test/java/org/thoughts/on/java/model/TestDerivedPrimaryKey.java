package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDerivedPrimaryKey {

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

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
	public void derivedPrimaryKey() {
		log.info("... derivedPrimaryKey ...");

		// Persist a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Review r = new Review();
		r.setId(new ReviewId());
		r.getId().setUserName("peter");
		r.setBook(em.find(Book.class, 1L));

		r.setComment("This is a comment");

		em.persist(r);

		em.getTransaction().commit();
		em.close();

		// Read the Review
		em = emf.createEntityManager();
		em.getTransaction().begin();

		r = em.find(Review.class, new ReviewId("peter", 1L));
		Assertions.assertNotNull(r);

		em.getTransaction().commit();
		em.close();
	}
}
