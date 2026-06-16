package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultipleId {

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
	public void multipleId() {
		log.info("... multipleId ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		MultiIdentifierLoadAccess<Book> multi = session.byMultipleIds(Book.class);
		List<Book> books = multi.multiLoad(1L, 2L, 3L);
		
		Assertions.assertEquals(3, books.size());

		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void multipleIdWithBatchSize() {
		log.info("... multipleIdWithBatchSize ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		MultiIdentifierLoadAccess<Book> multi = session.byMultipleIds(Book.class);
		List<Book> books = multi.withBatchSize(2).multiLoad(1L, 2L, 3L);
		
		Assertions.assertEquals(3, books.size());

		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void multipleIdWithSessionCheck() {
		log.info("... multipleIdWithSessionCheck ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		@SuppressWarnings("unused")
		Book b = em.find(Book.class, 1L);
		
		MultiIdentifierLoadAccess<Book> multi = session.byMultipleIds(Book.class);
		List<Book> books = multi.enableSessionCheck(true).multiLoad(1L, 2L, 3L);
		
		Assertions.assertEquals(3, books.size());

		em.getTransaction().commit();
		em.close();
	}
}
