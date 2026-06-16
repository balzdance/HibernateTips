package org.thoughts.on.java.model;

/**
 * Helper that backs the H2 stored procedure (function) "calculate".
 *
 * PostgreSQL stored procedures/functions do not exist on H2. On H2 a stored
 * procedure is defined as an ALIAS for a static Java method:
 *
 * <pre>
 * CREATE ALIAS calculate FOR "org.thoughts.on.java.model.Calculator.calculate";
 * </pre>
 *
 * The original PostgreSQL example used an OUT parameter to return the sum.
 * H2 does not support OUT parameters, so the function returns the sum as its
 * return value instead. Hibernate maps the single JPA OUT parameter to this
 * function return value when it issues {@code { ? = call calculate(?, ?) }}.
 */
public class Calculator {

	public static double calculate(double x, double y) {
		return x + y;
	}
}
