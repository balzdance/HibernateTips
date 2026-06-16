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
import jakarta.persistence.criteria.SetJoin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestCriteriaQuery {

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
	public void getBooks() {
		log.info("... getBooks ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Book> cq = cb.createQuery(Book.class);
		Root<Book> root = cq.from(Book.class);
		SetJoin<Book, Author> authors = root.join(Book_.authors);
		
		ParameterExpression<String> paramFirstName = cb.parameter(String.class);
		ParameterExpression<String> paramLastName = cb.parameter(String.class);
		cq.where(
			cb.and(
				cb.equal(authors.get(Author_.firstName), paramFirstName), 
				cb.equal(authors.get(Author_.lastName), paramLastName)));
		
		TypedQuery<Book> query = em.createQuery(cq);
		query.setParameter(paramFirstName, "Thorben");
		query.setParameter(paramLastName, "Janssen");
		List<Book> books = query.getResultList();
		Assertions.assertEquals(1, books.size());
		
		for (Book b : books) {
			log.info("{}", b);
		}
		
		em.getTransaction().commit();
		em.close();
	}
}
