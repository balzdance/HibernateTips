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

public class TestJPQLUpdate {

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
	public void updateBookPrices() {
		log.info("... updateBookPrices ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		logBookPrices(em);

		Query query = em.createQuery("UPDATE Book b SET b.price = b.price*1.1");
		query.executeUpdate();

		logBookPrices(em);

		em.getTransaction().commit();
		em.close();
	}

	private void logBookPrices(EntityManager em) {
		@SuppressWarnings("unchecked")
		List<Object[]> books = em.createQuery("SELECT b.title, b.price FROM Book b").getResultList();
		for (Object[] b : books) {
			log.info(b[0] + ": " + b[1]);
		}
	}
}
