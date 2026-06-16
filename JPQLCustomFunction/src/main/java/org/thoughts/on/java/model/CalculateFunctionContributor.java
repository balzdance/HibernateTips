package org.thoughts.on.java.model;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.boot.model.FunctionContributor;
import org.hibernate.query.sqm.function.SqmFunctionRegistry;
import org.hibernate.type.spi.TypeConfiguration;

/**
 * Registers a custom SQL function "calculate(x, y)" that returns the sum of its
 * two arguments. In the original PostgreSQL-based example this was a plpgsql
 * stored function; on H2 we map it to the SQL expression "(?1+?2)" so the
 * example stays database-independent.
 *
 * Hibernate 6 discovers this contributor through the
 * META-INF/services/org.hibernate.boot.model.FunctionContributor service file.
 */
public class CalculateFunctionContributor implements FunctionContributor {

	@Override
	public void contributeFunctions(FunctionContributions functionContributions) {
		final SqmFunctionRegistry registry = functionContributions.getFunctionRegistry();
		final TypeConfiguration types = functionContributions.getTypeConfiguration();

		registry.patternDescriptorBuilder("calculate", "(?1+?2)")
				.setExactArgumentCount(2)
				.setInvariantType(types.getBasicTypeForJavaType(Double.class))
				.register();
	}
}
