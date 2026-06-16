package org.thoughts.on.java.model;

import java.time.LocalDateTime;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestGeneratedColumn {

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
	public void createAuthor() throws InterruptedException {
		log.info("... createAuthor ...");

		EntityManager em = emf.createEntityManager();

		// Transaction 1
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		em.persist(a);

		em.getTransaction().commit();

		log.info("{}", a);
		LocalDateTime creationTime = a.getLastUpdate();
		Assertions.assertNotNull(creationTime);

		// Make sure the update timestamp is measurably different from the insert one
		Thread.sleep(10);

		// Transaction 2
		em.getTransaction().begin();

		a = em.find(Author.class, a.getId());
		a.setFirstName("Changed Firstname");

		em.getTransaction().commit();

		log.info("{}", a);
		LocalDateTime updateTime = a.getLastUpdate();
		Assertions.assertNotNull(updateTime);
		Assertions.assertNotEquals(creationTime, updateTime);

		em.close();
	}
}
