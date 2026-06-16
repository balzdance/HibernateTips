package org.thoughts.on.java.model;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestInheritance {

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
	public void testInheritance() {
		log.info("... testInheritance ...");

		// persist a new Book entity
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = new Book();
		b.setTitle("Hibernate in Practice");
		b.setNumPages(200);
		b.setPublishingDate(LocalDate.of(2017, 4, 4));
		
		em.persist(b);
		
		em.getTransaction().commit();
		em.close();
		
		
		// read the Book entity
		em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Book> q = em.createQuery("SELECT b FROM Book b WHERE b.id = :id", Book.class);
		q.setParameter("id", 1L);
		b = q.getSingleResult();
		Assertions.assertTrue(b instanceof Book);
		Assertions.assertEquals(Long.valueOf(1), ((Book)b).getId());

		log.info("{}", b);
		
		em.getTransaction().commit();
		em.close();
	}
}
