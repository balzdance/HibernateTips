package org.thoughts.on.java.model;

import java.time.LocalDate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestAttributeConverter {

	Logger log = LoggerFactory.getLogger(this.getClass());

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
	public void persistAndLoad() {
		log.info("... persistAndLoad ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("John");
		a.setLastName("Doe");
		a.setDateOfBirth(LocalDate.of(1970, 1, 1));
		
		em.persist(a);
		
		em.getTransaction().commit();
		em.close();
		
		em = emf.createEntityManager();
		em.getTransaction().begin();

		a = em.find(Author.class, a.getId());
		log.info("{}", a);
		
		em.getTransaction().commit();
		em.close();
	}
}
