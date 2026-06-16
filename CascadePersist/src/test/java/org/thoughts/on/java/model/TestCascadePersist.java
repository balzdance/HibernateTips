package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCascadePersist {

	Logger log = LoggerFactory.getLogger(this.getClass());

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
	public void testCascade() {
		log.info("... testCascade ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("John");
		a.setLastName("Doe");
		
		Book b1 = new Book();
		b1.setTitle("John's first book");
		a.getBooks().add(b1);
		
		Book b2 = new Book();
		b2.setTitle("John's second book");
		a.getBooks().add(b2);

		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
	}
}
