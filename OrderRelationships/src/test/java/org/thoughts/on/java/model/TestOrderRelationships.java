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

public class TestOrderRelationships {

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
	public void orderAuthors() {
		log.info("... orderAuthors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 2L);
		Author[] authors = b.getAuthors().toArray(new Author[3]);
		Assertions.assertEquals("Bauer", authors[0].getLastName());
		Assertions.assertEquals("Gregory", authors[1].getLastName());
		Assertions.assertEquals("King", authors[2].getLastName());
		for (Author a : authors) {
			log.info(a.getLastName() + ", id: " + a.getId());
		}

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void fetchBooksAndAuthors() {
		log.info("... fetchBooksAndAuthors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.createQuery("SELECT b FROM Book b JOIN FETCH b.authors a WHERE b.id = 2", Book.class).getSingleResult();
		Author[] authors = b.getAuthors().toArray(new Author[3]);
		Assertions.assertEquals("Bauer", authors[0].getLastName());
		Assertions.assertEquals("Gregory", authors[1].getLastName());
		Assertions.assertEquals("King", authors[2].getLastName());
		for (Author a : authors) {
			log.info(a.getLastName() + ", id: " + a.getId());
		}

		em.getTransaction().commit();
		em.close();
	}
}
