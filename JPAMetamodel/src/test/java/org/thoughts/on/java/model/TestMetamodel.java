package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestMetamodel {

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
	public void getBooks() {
		log.info("... getBooks ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		Root<Book> root = cq.from(Book.class);
		cq.multiselect(root.get(Book_.title), root.get(Book_.publishingDate));

		List<Tuple> results = em.createQuery(cq).getResultList();

		for (Tuple r : results) {
			log.info(r.get(0) + " was published on " + r.get(1));
		}

		em.getTransaction().commit();
		em.close();
	}
}
