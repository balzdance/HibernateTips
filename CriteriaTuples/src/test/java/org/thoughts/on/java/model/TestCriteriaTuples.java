package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Tuple;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestCriteriaTuples {

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
	public void selectTuples() {
		log.info("... selectTuples ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> q = cb.createTupleQuery();
		Root<Author> author = q.from(Author.class);
		q.multiselect(author.get(Author_.firstName).alias("firstName"),
						author.get(Author_.lastName).alias("lastName"));

		TypedQuery<Tuple> query = em.createQuery(q);
		List<Tuple> authorNames = query.getResultList();

		for (Tuple authorName : authorNames) {
			log.info("{} {}", authorName.get("firstName"), authorName.get("lastName"));
		}

		em.getTransaction().commit();
		em.close();
	}
}
