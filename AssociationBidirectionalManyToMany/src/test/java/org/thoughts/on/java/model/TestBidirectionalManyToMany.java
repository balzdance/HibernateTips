package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestBidirectionalManyToMany {

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
	public void bidirectionalManyToMany() {
		log.info("... bidirectionalManyToMany ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");

		a.getBooks().add(b);
		b.getAuthors().add(a);

		em.persist(a);

		em.getTransaction().commit();
		em.close();

		// Get Book entity with Authors
		em = emf.createEntityManager();
		em.getTransaction().begin();

		b = em.find(Book.class, 1L);

		List<Author> authors = b.getAuthors();
		Assertions.assertTrue(authors.get(0).getBooks().contains(b));
		Assertions.assertTrue(authors.contains(a));

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void bidirectionalManyToManyWithHelperMethod() {
		log.info("... bidirectionalManyToManyWithHelperMethod ...");

		// Add a new Review
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");

		a.addBook(b);

		em.persist(a);

		em.getTransaction().commit();
		em.close();

		// Get Book entity with Authors
		em = emf.createEntityManager();
		em.getTransaction().begin();

		b = em.find(Book.class, 1L);

		List<Author> authors = b.getAuthors();
		Assertions.assertTrue(authors.get(0).getBooks().contains(b));
		Assertions.assertTrue(authors.contains(a));

		em.getTransaction().commit();
		em.close();
	}
}
