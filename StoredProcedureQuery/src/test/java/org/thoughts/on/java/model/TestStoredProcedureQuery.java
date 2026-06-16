package org.thoughts.on.java.model;

import java.sql.Types;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.Persistence;
import jakarta.persistence.StoredProcedureQuery;

import org.hibernate.procedure.ProcedureCall;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestStoredProcedureQuery {

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
		log.info("... calculate ...");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		// define the stored procedure (an H2 ALIAS to a static Java method)
		StoredProcedureQuery query = em.createStoredProcedureQuery("calculate");
		// On H2 a stored procedure is a function ALIAS that returns its result
		// instead of using an OUT parameter (H2 does not support OUT params).
		// Mark the call as a function so Hibernate renders "{ ? = call calculate(?, ?) }".
		query.unwrap(ProcedureCall.class).markAsFunctionCall(Types.DOUBLE);
		query.registerStoredProcedureParameter("x", Double.class, ParameterMode.IN);
		query.registerStoredProcedureParameter("y", Double.class, ParameterMode.IN);

		// set input parameter
		query.setParameter("x", 1.23d);
		query.setParameter("y", 4d);

		// call the stored procedure and get the result (the function return value)
		Double sum = (Double) query.getSingleResult();
		log.info("Calculation result: 1.23 + 4 = " + sum);
		Assertions.assertEquals(Double.valueOf(5.23d), sum);

		em.getTransaction().commit();
		em.close();
	}
}
