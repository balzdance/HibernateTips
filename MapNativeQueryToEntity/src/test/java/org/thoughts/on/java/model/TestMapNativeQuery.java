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
	public void implicitMapping() {
		log.info("... implicitMapping ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = (Book) em.createNativeQuery("SELECT * FROM book b WHERE id = 1", Book.class).getSingleResult();
		Assertions.assertTrue(b instanceof Book);
		Assertions.assertEquals(Long.valueOf(1), b.getId());
		Assertions.assertEquals("Hibernate Tips", b.getTitle());

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void explicitMapping() {
		log.info("... explicitMapping ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = (Book) em.createNativeQuery("SELECT id as bookId, version, title, publishingDate, publisherid FROM book b WHERE id = 1", "BookMapping").getSingleResult();
		Assertions.assertTrue(b instanceof Book);
		Assertions.assertEquals(Long.valueOf(1), b.getId());
		Assertions.assertEquals("Hibernate Tips", b.getTitle());

		em.getTransaction().commit();
		em.close();
	}
}
