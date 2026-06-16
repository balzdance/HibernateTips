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

public class TestPrimaryKey {

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
	public void testPrimaryKey() {
		log.info("... testPrimaryKey ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setId(1L);
		a.setFirstName("Thorben");
		a.setLastName("Janssen");

		log.info("Persist new Author entity.");
		em.persist(a);

		log.info("Call flush");
		em.flush();

		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();

		a = em.find(Author.class, 1L);
		Assertions.assertEquals(Long.valueOf(1), a.getId());

		em.getTransaction().commit();
		em.close();
	}
}
