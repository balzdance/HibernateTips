package org.thoughts.on.java.model;

import jakarta.persistence.EntityGraph;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestEntityGraph {

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

	@SuppressWarnings("unchecked")
	@Test
	public void selectWithEntityGraph() {
		log.info("... selectWithEntityGraph ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		EntityGraph<Author> graph = em.createEntityGraph(Author.class);
		graph.addAttributeNodes(Author_.books);

		TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a WHERE a.id = 1", Author.class);
		q.setHint("jakarta.persistence.fetchgraph", graph);
		Author a = q.getSingleResult();

		em.getTransaction().commit();
		em.close();

		log.info("{} {} wrote {} books.", a.getFirstName(), a.getLastName(), a.getBooks().size());
	}

	@Test
	public void selectWithNamedEntityGraph() {
		log.info("... selectWithNamedEntityGraph ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		EntityGraph<?> graph = em.createEntityGraph("graph.AuthorBooks");
		TypedQuery<Author> q = em.createQuery("SELECT a FROM Author a WHERE a.id = 1", Author.class);
		q.setHint("jakarta.persistence.fetchgraph", graph);
		Author a = q.getSingleResult();

		em.getTransaction().commit();
		em.close();

		log.info("{} {} wrote {} books.", a.getFirstName(), a.getLastName(), a.getBooks().size());
	}
}
