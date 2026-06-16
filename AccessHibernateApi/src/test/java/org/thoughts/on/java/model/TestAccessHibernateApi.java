package org.thoughts.on.java.model;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestAccessHibernateApi {

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

	@SuppressWarnings("unused")
	@Test
	public void accessHibernateSession() {
		log.info("... accessHibernateSession ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		Session session = em.unwrap(Session.class);
		
		em.getTransaction().commit();
		em.close();
	}
	
	@SuppressWarnings("unused")
	@Test
	public void accessHibernateSessionFactory() {
		log.info("... accessHibernateSessionFactory ...");

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		SessionFactory sessionFactory = em.getEntityManagerFactory().unwrap(SessionFactory.class);
		
		em.getTransaction().commit();
		em.close();
	}
}
