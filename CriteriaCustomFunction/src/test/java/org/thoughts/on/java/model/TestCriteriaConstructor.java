package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
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
	public void callFunction() {
		log.info("... callFunction ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		
		ParameterExpression<Double> doubleParam1 = cb.parameter(Double.class);
		ParameterExpression<Double> doubleParam2 = cb.parameter(Double.class);
		cq.where(cb.greaterThan(doubleParam2, cb.function("calculate", Double.class, root.get(Book_.price), doubleParam1)));

		TypedQuery<Book> q = em.createQuery(cq);
		q.setParameter(doubleParam1, 10.0D);
		q.setParameter(doubleParam2, 40.0D);
		List<Book> books = q.getResultList();
		
		for (Book b : books) {
			log.info("{}", b);
		}

		em.getTransaction().commit();
		em.close();
	}
}
