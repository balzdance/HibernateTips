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

public class TestQueryPagination {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private EntityManagerFactory emf;

	@BeforeEach
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		seedAuthors();
	}

	@AfterEach
	public void close() {
		emf.close();
	}

	private void seedAuthors() {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		for (int i = 1; i <= 10; i++) {
			Author a = new Author();
			a.setFirstName("FirstName" + i);
			a.setLastName("LastName" + i);
			em.persist(a);
		}
		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void first5Authors() {
		log.info("... first5Authors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Author> authors = em.createQuery("SELECT a FROM Author a ORDER BY a.id ASC", Author.class)
									.setMaxResults(5)
									.setFirstResult(0)
									.getResultList();
		Assertions.assertEquals(5, authors.size(), "Expected a list of 5 authors.");
		authors.forEach(a -> log.info(a.getFirstName() + " " + a.getLastName()));

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void next5Authors() {
		log.info("... next5Authors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Author> authors = em.createQuery("SELECT a FROM Author a ORDER BY a.id ASC", Author.class)
									.setMaxResults(5)
									.setFirstResult(5)
									.getResultList();
		Assertions.assertEquals(5, authors.size(), "Expected a list of 5 authors.");
		authors.forEach(a -> log.info(a.getFirstName() + " " + a.getLastName()));

		em.getTransaction().commit();
		em.close();
	}
}
