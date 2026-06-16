package org.thoughts.on.java.model;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestStatistics {

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
	public void logStatistics() {
		log.info("... logStatistics ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class).getResultList();

		for (Author a : authors) {
			log.info(a.getFirstName() + " " + a.getLastName() + " wrote " + a.getBooks().size());
		}
		
		em.getTransaction().commit();
		em.close();
	}
	
	@Test
	public void statisticsAPI() {
		log.info("... statisticsAPI ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		List<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class).getResultList();

		for (Author a : authors) {
			log.info(a.getFirstName() + " " + a.getLastName() + " wrote " + a.getBooks().size());
		}
		
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		Statistics stats = sessionFactory.getStatistics();
		long queryCount = stats.getQueryExecutionCount();
		long collectionFetchCount = stats.getCollectionFetchCount();
		log.info("QueryCount: "+queryCount);
		log.info("CollectionFetchCount: "+collectionFetchCount);
		
		em.getTransaction().commit();
		em.close();
	}
}
