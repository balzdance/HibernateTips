package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaUpdate;
import jakarta.persistence.criteria.Root;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCriteriaUpdate {

	private final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(this.getClass());

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

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaUpdate<Book> update = cb.createCriteriaUpdate(Book.class);
		Root<Book> root = update.from(Book.class);
		update.set(Book_.price, cb.prod(root.get(Book_.price), 1.1));

		Query query = em.createQuery(update);
		query.executeUpdate();

		logBookPrices(em);

		em.getTransaction().commit();
		em.close();
	}

	private void logBookPrices(EntityManager em) {
		@SuppressWarnings("unchecked")
		List<Object[]> books = em.createQuery("SELECT b.title, b.price FROM Book b").getResultList();
		for (Object[] b : books) {
			log.info("{}: {}", b[0], b[1]);
		}
	}
}
