package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTreat {

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

	@SuppressWarnings("unchecked")
	@Test
	public void testTreat() {
		log.info("... testTreat ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Object[]> result = em
				.createQuery(
						"SELECT a, p FROM Author a JOIN a.publications p WHERE treat(p AS Book).title LIKE '%Java%'")
				.getResultList();

		for (Object[] o : result) {
			log.info(o[0] + " wrote " + o[1]);
		}

		em.getTransaction().commit();
		em.close();
	}
}
