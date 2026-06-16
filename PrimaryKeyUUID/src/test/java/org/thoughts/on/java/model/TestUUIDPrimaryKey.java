package org.thoughts.on.java.model;

import java.util.UUID;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestUUIDPrimaryKey {

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
	public void testUUIDPrimaryKeyV4() {
		log.info("... testUUIDPrimaryKeyV4 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");

		log.info("Persist new Author entity.");
		em.persist(a);

		log.info("Call flush");
		em.flush();

		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();

		UUID uuid = a.getId();

		a = em.find(Author.class, uuid);
		Assertions.assertEquals(uuid, a.getId());

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void testUUIDPrimaryKeyV1() {
		log.info("... testUUIDPrimaryKeyV1 ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Book b = new Book();
		b.setTitle("Hibernate Tips");

		log.info("Persist new Book entity.");
		em.persist(b);

		log.info("Call flush");
		em.flush();

		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();

		UUID uuid = b.getId();

		b = em.find(Book.class, uuid);
		Assertions.assertEquals(uuid, b.getId());

		em.getTransaction().commit();
		em.close();
	}
}
