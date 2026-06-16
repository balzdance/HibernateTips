package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestJoinUnassociatedEntities {

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
	public void joinUnassociated() {
		log.info("... joinUnassociated ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createQuery("SELECT b.title, count(r.id) FROM Book b INNER JOIN Review r ON r.fkBook = b.id GROUP BY b.title");
		Object[] r = (Object[]) q.getSingleResult();
		log.info(r[0] + " received " + r[1] + " reviews.");
		
		em.getTransaction().commit();
		em.close();
	}
}
