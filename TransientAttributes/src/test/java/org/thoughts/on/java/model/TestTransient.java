package org.thoughts.on.java.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTransient {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private EntityManagerFactory emf;

	private Long authorId;
	private LocalDate dateOfBirth;

	@BeforeEach
	public void init() {
		emf = Persistence.createEntityManagerFactory("my-persistence-unit");
		seedAuthor();
	}

	@AfterEach
	public void close() {
		emf.close();
	}

	private void seedAuthor() {
		// Born 43 years ago today so the calculated age is deterministic.
		dateOfBirth = LocalDate.now().minusYears(43);

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		a.setDateOfBirth(dateOfBirth);
		em.persist(a);

		em.getTransaction().commit();
		em.close();

		authorId = a.getId();
	}

	private int expectedAge() {
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}

	@Test
	public void emFindAge() {
		log.info("... emFindAge ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, authorId);
		Assertions.assertEquals(expectedAge(), a.getAge());
		log.info(a.getFirstName() + " " + a.getLastName() + " is " + a.getAge() + " years old.");

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void emFindCalculatedAge() {
		log.info("... emFindCalculatedAge ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.find(Author.class, authorId);
		Assertions.assertEquals(expectedAge(), a.getCalculatedAge());
		log.info(a.getFirstName() + " " + a.getLastName() + " is " + a.getCalculatedAge() + " years old.");

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void query() {
		log.info("... query ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Author a = em.createQuery("SELECT a FROM Author a WHERE a.id = :id", Author.class)
				.setParameter("id", authorId)
				.getSingleResult();
		Assertions.assertEquals(expectedAge(), a.getAge());
		log.info(a.getFirstName() + " " + a.getLastName() + " is " + a.getAge() + " years old.");

		em.getTransaction().commit();
		em.close();
	}
}
