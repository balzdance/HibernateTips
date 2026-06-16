package org.thoughts.on.java.model;

import java.util.List;
import java.util.stream.Stream;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStream {

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
	public void resultsAsList() {
		log.info("... resultsAsList ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		List<Book> books = session.createQuery("SELECT b FROM Book b", Book.class).getResultList();
		books.stream()
		    .map(b -> b.getTitle() + " was published on " + b.getPublishingDate())
		    .forEach(m -> log.info(m));

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void resultsAsStream() {
		log.info("... resultsAsStream ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Session session = em.unwrap(Session.class);

		Stream<Book> books = session.createQuery("SELECT b FROM Book b", Book.class).getResultStream();
		books.map(b -> b.getTitle() + " was published on " + b.getPublishingDate())
		    .forEach(m -> log.info(m));

		em.getTransaction().commit();
		em.close();
	}
}
