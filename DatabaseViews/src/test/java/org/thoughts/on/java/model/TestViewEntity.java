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

public class TestViewEntity {

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
	public void selectFromView() {
		log.info("... selectFromView ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<BookView> bvs = em.createQuery("SELECT v FROM BookView v", BookView.class)
				.getResultList();

		for (BookView bv : bvs) {
			log.info("{} was written by {}", bv.getTitle(), bv.getAuthors());
		}

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void updateView() {
		log.info("... updateView ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		BookView bv = em.find(BookView.class, 1L);
		log.info("{}", bv);
		// BookView is mapped to a read-only view (@Immutable); the change is ignored.
		bv.setTitle("updated");

		em.getTransaction().commit();
		em.close();

		em = emf.createEntityManager();
		em.getTransaction().begin();

		BookView bookupdate = em.find(BookView.class, 1L);
		log.info("{}", bookupdate);

		em.getTransaction().commit();
		em.close();
	}
}
