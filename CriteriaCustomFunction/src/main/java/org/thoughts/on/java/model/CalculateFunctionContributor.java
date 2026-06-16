package org.thoughts.on.java.model;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;

/**
 * Registers the custom {@code calculate(x, y)} SQL function used by the
 * Criteria query test. In Hibernate 6 custom functions are contributed via the
 * {@link FunctionContributor} SPI (discovered through
 * {@code META-INF/services/org.hibernate.boot.model.FunctionContributor})
 * instead of the removed {@code MetadataBuilder.applySqlFunction} / dialect
 * registration used in Hibernate 5.
 *
 * <p>The function is rendered as {@code (?1 + ?2)} which works on H2 (and any
 * other database) without a database-side stored function.</p>
 */
public class CalculateFunctionContributor implements FunctionContributor {

	@Override
	public void contributeFunctions(FunctionContributions functionContributions) {
		functionContributions.getFunctionRegistry()
				.registerPattern("calculate", "(?1 + ?2)");
	}
}
