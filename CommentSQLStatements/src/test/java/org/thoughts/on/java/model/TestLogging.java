package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLogging {

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
	public void selectAuthorsJPQL() {
		log.info("... selectAuthorsJPQL ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a WHERE a.id = :id", Author.class);
		q.setParameter("id", 1L);
		q.setHint("org.hibernate.comment", "This is my comment");
		q.getSingleResult();

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void selectAuthorsNative() {
		log.info("... selectAuthorsNative ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Query q = em.createNativeQuery("SELECT * FROM Author a WHERE a.id = :id", Author.class);
		q.setParameter("id", 1L);
		q.setHint("org.hibernate.comment", "This is my comment");
		q.getSingleResult();

		em.getTransaction().commit();
		em.close();
	}

	@Test
	public void selectAuthorsCriteria() {
		log.info("... selectAuthorsCriteria ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Author> cq = cb.createQuery(Author.class);
		Root<Author> root = cq.from(Author.class);
		cq.select(root);
		ParameterExpression<Long> idParam = cb.parameter(Long.class, "id");
		cq.where(cb.equal(root.get("id"), idParam));
		TypedQuery<Author> q = em.createQuery(cq);
		q.setParameter("id", 1L);
		q.setHint("org.hibernate.comment", "This is my comment");
		q.getSingleResult();

		em.getTransaction().commit();
		em.close();
	}
}
