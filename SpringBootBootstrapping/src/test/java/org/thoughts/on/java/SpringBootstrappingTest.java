package org.thoughts.on.java;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.thoughts.on.java.model.Author;

import jakarta.persistence.EntityManager;

@SpringBootTest
public class SpringBootstrappingTest {

	private static final Logger log = LoggerFactory.getLogger(SpringBootstrappingTest.class);

	@Autowired
	private EntityManager em;

	@Test
	@Transactional
	@Commit
	public void accessHibernateSession() {
		log.info("... accessHibernateSession ...");

		Author a = new Author();
		a.setFirstName("Thorben");
		a.setLastName("Janssen");
		em.persist(a);
	}
}
