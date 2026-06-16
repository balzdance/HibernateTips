package org.thoughts.on.java.model;

import java.util.HashMap;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestQueryTimeout {

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

	@SuppressWarnings("unused")
	@Test
	public void queryTimeoutOnQuery() {
		log.info("... queryTimeoutOnQuery ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class)
		  .setHint("jakarta.persistence.query.timeout", 1)
		  .getResultList();

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void queryTimeoutOnEMfind() {
		log.info("... queryTimeoutOnEMfind ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		HashMap<String, Object> hints = new HashMap<>();
		hints.put("jakarta.persistence.query.timeout", 1);

		em.find(Author.class, 50L, hints);

		em.getTransaction().commit();
		em.close();
	}
}
