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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCriteriaFunction {

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
	public void callSizeFunction() {
		log.info("... callSizeFunction ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Tuple> cq = cb.createTupleQuery();
		Root<Author> root = cq.from(Author.class);
		cq.multiselect(root, cb.size(root.get(Author_.books)));
		cq.groupBy(root.get(Author_.id));
		
		TypedQuery<Tuple> q = em.createQuery(cq);
		List<Tuple> results = q.getResultList();
		
		for (Tuple r :  results) {
			log.info(r.get(0) + " wrote " +  r.get(1) + " books.");
		}

		em.getTransaction().commit();
		em.close();
	}
}
