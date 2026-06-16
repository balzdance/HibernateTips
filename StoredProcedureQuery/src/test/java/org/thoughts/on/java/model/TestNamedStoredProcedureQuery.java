package org.thoughts.on.java.model;

import java.sql.Types;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;

import org.hibernate.procedure.ProcedureCall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestNamedStoredProcedureQuery {

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
	public void calculate() {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		StoredProcedureQuery query = em.createNamedStoredProcedureQuery("calculate");
		// On H2 "calculate" is a function ALIAS returning its result (no OUT param).
		// Mark the call as a function so Hibernate reads the return value.
		query.unwrap(ProcedureCall.class).markAsFunctionCall(Types.DOUBLE);
		query.setParameter("x", 1.23d);
		query.setParameter("y", 4d);
		Double sum = (Double) query.getSingleResult();
		log.info("Calculation result: 1.23 + 4 = " + sum);
		Assertions.assertEquals(Double.valueOf(5.23d), sum);

		em.getTransaction().commit();
		em.close();
	}
}
