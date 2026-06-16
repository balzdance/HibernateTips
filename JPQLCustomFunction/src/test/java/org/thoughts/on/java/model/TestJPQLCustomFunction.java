package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestJPQLCustomFunction {

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
	public void callFunction() {
		log.info("... callFunction ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Book> q = em.createQuery(
				"SELECT b "
				+ "FROM Book b "
				+ "WHERE :double2 > function('calculate', b.price, :double1)"
				, Book.class);
		q.setParameter("double1", 10.0D);
		q.setParameter("double2", 40.0D);
		List<Book> books = q.getResultList();

		for (Book b : books) {
			log.info("{}", b);
		}

		em.getTransaction().commit();
		em.close();
	}
}
