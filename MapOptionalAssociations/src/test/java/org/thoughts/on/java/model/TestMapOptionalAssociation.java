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

public class TestMapOptionalAssociation {

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
	public void testOptional() {
		log.info("... testOptional ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		Assertions.assertTrue(b.getPublisher().isPresent());
		log.info(b.getTitle() + " was published by " + b.getPublisher().get().getName());

		b = em.find(Book.class, 2L);
		Assertions.assertFalse(b.getPublisher().isPresent());
		log.info(b.getTitle() + " has no publisher");

		em.getTransaction().commit();
		em.close();
	}
}
