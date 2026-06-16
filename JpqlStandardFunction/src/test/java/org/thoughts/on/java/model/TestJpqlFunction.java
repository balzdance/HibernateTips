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

public class TestJpqlFunction {

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
	public void callSizeFunction() {
		log.info("... callSizeFunction ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery("SELECT a, size(a.books) FROM Author a GROUP BY a.id");
		@SuppressWarnings("unchecked")
		List<Object[]> results = q.getResultList();

		for (Object[] r :  results) {
			log.info(r[0] + " wrote " +  r[1] + " books.");
		}

		em.getTransaction().commit();
		em.close();
	}
}
