package org.thoughts.on.java.model;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestDateAndTime {

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
	public void persistDateAndTime() {
		log.info("... persistDateAndTime ...");

		EntityManager em = emf.createEntityManager();

		// Persist a new Book entity
		em.getTransaction().begin();

		Book b = new Book();
		b.setTitle("Hibernate Tips");
		b.setPublishingDate(LocalDate.of(2017, 4, 4));
		em.persist(b);

		log.info("Persisted: {}", b);

		em.getTransaction().commit();

		// Read the new entity in a 2nd transaction
		em.getTransaction().begin();

		Book b2 = em.find(Book.class, b.getId());
		Assertions.assertEquals(b.getId(), b2.getId());
		Assertions.assertEquals(b.getPublishingDate(), b2.getPublishingDate());

		log.info("Read: {}", b2);

		em.getTransaction().commit();

		em.close();
	}
}
