package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestNaturalId {

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
	public void naturalId() {
		log.info("... naturalId ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		Book b = session.byNaturalId(Book.class)
				.using(Book_.isbn.getName(), "123-4567890123").load();
		Assertions.assertEquals(Long.valueOf(1), b.getId());

		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void simpleNaturalId() {
		log.info("... simpleNaturalId ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		Book b = session.bySimpleNaturalId(Book.class)
				.load("123-4567890123");
		Assertions.assertEquals(Long.valueOf(1), b.getId());

		em.getTransaction().commit();
		em.close();
	}
}
