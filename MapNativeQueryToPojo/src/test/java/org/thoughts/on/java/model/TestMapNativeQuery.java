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

public class TestMapNativeQuery {

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
	public void explicitMapping() {
		log.info("... explicitMapping ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		BookValue b = (BookValue) em.createNativeQuery("SELECT b.publishingDate as date, b.title, b.id FROM book b WHERE b.id = 1", "BookValueMapping").getSingleResult();
		Assertions.assertTrue(b instanceof BookValue);
		Assertions.assertEquals("Effective Java", b.getTitle());

		em.getTransaction().commit();
		em.close();
	}
}
