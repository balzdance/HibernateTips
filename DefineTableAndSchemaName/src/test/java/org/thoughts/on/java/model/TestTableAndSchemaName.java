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

public class TestTableAndSchemaName {

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
	public void selectFromView() {
		log.info("... selectFromView ...");

		// Persist a new Author entity
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("firstName");
		a.setLastName("lastName");
		em.persist(a);

		em.getTransaction().commit();
		em.close();

		// Use a new EntityManager to read the Author entity from the database and not the cache
		em = emf.createEntityManager();
		em.getTransaction().begin();

		a = em.createQuery("SELECT a FROM Author a WHERE firstName = 'firstName'", Author.class).getSingleResult();
		Assertions.assertNotNull(a);
		log.info("{}", a);

		em.getTransaction().commit();
		em.close();
	}
}
