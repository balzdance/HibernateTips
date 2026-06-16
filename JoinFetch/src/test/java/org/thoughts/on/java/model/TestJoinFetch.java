package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.hibernate.LazyInitializationException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJoinFetch {

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
	public void selectFromWithoutJoinFetch() {
		log.info("... selectFromWithoutJoinFetch ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.createQuery("SELECT a FROM Author a WHERE id = 1", Author.class).getSingleResult();

		log.info("Commit transaction and close Session");
		em.getTransaction().commit();
		em.close();

		Assertions.assertThrows(LazyInitializationException.class, () -> {
			try {
				log.info(a.getFirstName()+" "+a.getLastName()+" wrote "+a.getBooks().size()+" books.");
			} catch (Exception e) {
				log.error("Error", e);
				throw e;
			}
		});
	}

	@Test
	public void selectFromWithJoinFetch() {
		log.info("... selectFromWithJoinFetch ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.createQuery("SELECT a FROM Author a JOIN FETCH a.books WHERE a.id = 1", Author.class).getSingleResult();

		log.info("Commit transaction and close Session");
		em.getTransaction().commit();
		em.close();

		log.info(a.getFirstName()+" "+a.getLastName()+" wrote "+a.getBooks().size()+" books.");
	}
}
