package org.thoughts.on.java.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestHibernateBootstrapping {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void bootstrapping() {
		log.info("... bootstrapping ...");

		ServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();

		SessionFactory sessionFactory = new MetadataSources(standardRegistry)
			.addAnnotatedClass(Author.class).buildMetadata()
			.buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		session.persist(a);

		session.getTransaction().commit();
		session.close();
		sessionFactory.close();
	}
}
