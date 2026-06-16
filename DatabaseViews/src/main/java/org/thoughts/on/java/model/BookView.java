package org.thoughts.on.java.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

/**
 * Maps a read-only database view. Instead of relying on a database-specific
 * CREATE VIEW statement, the view is expressed as a Hibernate {@link Subselect}
 * so the example runs on any database (here: H2). {@link Synchronize} tells
 * Hibernate which tables back the view so it can flush pending changes before
 * querying it.
 */
@Entity
@Immutable
@Subselect("SELECT b.id AS id, b.version AS version, b.title AS title, "
		+ "b.publishingDate AS publishingDate, "
		+ "GROUP_CONCAT(a.firstName || ' ' || a.lastName SEPARATOR ', ') AS authors "
		+ "FROM Book b "
		+ "JOIN BookAuthor ba ON b.id = ba.bookId "
		+ "JOIN Author a ON a.id = ba.authorId "
		+ "GROUP BY b.id, b.version, b.title, b.publishingDate")
@Synchronize({ "Book", "Author", "BookAuthor" })
public class BookView {

	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version
	@Column(name = "version")
	private int version;

	@Column
	private String title;

	@Column
	@Temporal(TemporalType.DATE)
	private Date publishingDate;

	@Column
	private String authors;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof BookView)) {
			return false;
		}
		BookView other = (BookView) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPublishingDate() {
		return publishingDate;
	}

	public void setPublishingDate(Date publishingDate) {
		this.publishingDate = publishingDate;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
	}

	@Override
	public String toString() {
		return "BookView [id=" + id + ", version=" + version + ", title="
				+ title + ", publishingDate=" + publishingDate + ", authors="
				+ authors + "]";
	}
}
