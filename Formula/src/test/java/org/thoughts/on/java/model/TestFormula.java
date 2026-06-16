package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestFormula {

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
	public void emFind() {
		log.info("... emFind ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, 1L);
		Assertions.assertEquals(43, a.getAge());
		log.info("{} {} is {} years old.", a.getFirstName(), a.getLastName(), a.getAge());

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void query() {
		log.info("... query ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.createQuery("SELECT a FROM Author a WHERE a.id = 1", Author.class).getSingleResult();
		Assertions.assertEquals(43, a.getAge());
		log.info("{} {} is {} years old.", a.getFirstName(), a.getLastName(), a.getAge());

		em.getTransaction().commit();
		em.close();
	}
}
