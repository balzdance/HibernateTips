package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJPABootstrapping {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void bootstrapping() {
		log.info("... bootstrapping ...");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		em.find(Book.class, 1L);

		em.getTransaction().commit();
		em.close();
		emf.close();
	}
}
