package org.thoughts.on.java.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Version;

/*
 * On H2 the "calculate" stored procedure is an ALIAS for a static Java method
 * (org.thoughts.on.java.model.Calculator.calculate) created by create-procedure.sql.
 * H2 implements such procedures as functions that RETURN their result; it does not
 * support OUT parameters. The named query therefore only declares the two IN
 * parameters, and the test marks the call as a function call so Hibernate renders
 * it as a value-returning function and reads the return value.
 */
@Entity
@NamedStoredProcedureQuery(name = "calculate",
	procedureName = "calculate",
	parameters = {	@StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class, name = "x"),
					@StoredProcedureParameter(mode = ParameterMode.IN, type = Double.class, name = "y") })
public class Author {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
	
	@Version
	private int version;

	private String firstName;

	private String lastName;

	public Long getId() {
		return this.id;
	}

	public int getVersion() {
		return this.version;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Author)) {
			return false;
		}
		Author other = (Author) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		return 31;
	}
	
	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (firstName != null && !firstName.trim().isEmpty())
			result += "firstName: " + firstName;
		if (lastName != null && !lastName.trim().isEmpty())
			result += ", lastName: " + lastName;
		return result;
	}
}