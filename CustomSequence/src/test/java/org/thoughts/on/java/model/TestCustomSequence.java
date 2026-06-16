package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCustomSequence {

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
	public void useCustomSequence() {
		log.info("... useCustomSequence ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("firstName");
		a.setLastName("lastName");

		em.persist(a);

		em.getTransaction().commit();
		em.close();
	}
}
