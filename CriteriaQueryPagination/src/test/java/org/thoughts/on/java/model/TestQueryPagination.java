package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestQueryPagination {

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
	public void first5Authors() {
		log.info("... first5Authors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Define the CriteriaQuery
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		cq.orderBy(cb.asc(root.get(Book_.id)));
		
		// Execute query with pagination
		List<Book> books = em.createQuery(cq)
									.setMaxResults(5)
									.setFirstResult(0)
									.getResultList();
		Assertions.assertEquals(5, books.size(), "Expected a list of 5 books.");
		books.forEach(b -> log.info(b.getTitle()));

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void next5Authors() {
		log.info("... next5Authors ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// Define the CriteriaQuery
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		cq.orderBy(cb.asc(root.get(Book_.id)));
		
		// Execute query with pagination
		List<Book> books = em.createQuery(cq)
									.setMaxResults(5)
									.setFirstResult(5)
									.getResultList();
		Assertions.assertEquals(5, books.size(), "Expected a list of 5 books.");
		books.forEach(b -> log.info(b.getTitle()));

		em.getTransaction().commit();
		em.close();
	}
}
