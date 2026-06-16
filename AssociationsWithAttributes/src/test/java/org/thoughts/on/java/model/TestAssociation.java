package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAssociation {

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
	public void associationWithAttributes() {
		log.info("... associationWithAttributes ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = em.find(Book.class, 1L);
		Publisher p = em.find(Publisher.class, 1L);

		BookPublisher bp = new BookPublisher();
		bp.setId(new BookPublisherId());
		bp.setBook(b);
		bp.setPublisher(p);
		bp.setFormat(Format.PAPERBACK);

		em.persist(bp);

		em.getTransaction().commit();
		em.close();
	}
}
