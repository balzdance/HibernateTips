package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJPQLDelete {

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
	public void deleteBooks() {
		log.info("... deleteBooks ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		logBooks(em);

		Query query = em.createQuery("DELETE FROM Book b");
		query.executeUpdate();

		logBooks(em);

		em.getTransaction().commit();
		em.close();
	}

	private void logBooks(EntityManager em) {
		@SuppressWarnings("unchecked")
		List<String> titles = em.createQuery("SELECT b.title FROM Book b").getResultList();
		for (String title : titles) {
			log.info(title);
		}
	}
}
