package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCriteriaConstructor {

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
	public void selectPojo() {
		log.info("... selectPojo ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<AuthorValue> q = cb.createQuery(AuthorValue.class);
		Root<Author> root = q.from(Author.class);
		q.select(cb.construct(AuthorValue.class, root.get(Author_.firstName), root.get(Author_.lastName)));

		TypedQuery<AuthorValue> query = em.createQuery(q);
		List<AuthorValue> authors = query.getResultList();

		for (AuthorValue author : authors) {
			log.info(author.getFirstName() + " "
					+ author.getLastName());
		}

		em.getTransaction().commit();
		em.close();
	}
}
