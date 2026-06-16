package org.thoughts.on.java.model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.h2.api.Trigger;

/**
 * H2 trigger that mirrors the PostgreSQL {@code sync_lastupdate} trigger from
 * the original example: it sets the {@code lastupdate} column to the current
 * timestamp on every INSERT and UPDATE. This lets the {@code @Generated}
 * mapping on {@link Author#getLastUpdate()} read a database-generated value.
 */
public class SyncLastUpdateTrigger implements Trigger {

	private int lastUpdateIndex = -1;

	@Override
	public void init(Connection conn, String schemaName, String triggerName, String tableName, boolean before,
			int type) throws SQLException {
		// Resolve the column index of LASTUPDATE so we do not depend on column order.
		try (var rs = conn.getMetaData().getColumns(null, schemaName, tableName, null)) {
			while (rs.next()) {
				if ("LASTUPDATE".equalsIgnoreCase(rs.getString("COLUMN_NAME"))) {
					lastUpdateIndex = rs.getInt("ORDINAL_POSITION") - 1;
					break;
				}
			}
		}
	}

	@Override
	public void fire(Connection conn, Object[] oldRow, Object[] newRow) throws SQLException {
		if (newRow != null && lastUpdateIndex >= 0) {
			newRow[lastUpdateIndex] = Timestamp.valueOf(LocalDateTime.now());
		}
	}

	@Override
	public void close() throws SQLException {
		// nothing to do
	}

	@Override
	public void remove() throws SQLException {
		// nothing to do
	}
}
