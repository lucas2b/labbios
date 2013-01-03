package labbios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import labbios.db.DatabaseUtil;
import labbios.dto.Status;

public class StatusDAO extends DatabaseUtil {

	public Status procurarStatusPorID(int statusID)
			throws ClassNotFoundException, SQLException {
		ResultSet rs = getStatement().executeQuery(
				"Select * from STATUS where STATUS_ID=" + statusID);
		rs.next();
		return popularStatus(rs);
	}

	public Status popularStatus(ResultSet rs) throws SQLException {
		Status status = new Status();
		status.setSTATUS_ID(rs.getInt("STATUS_ID"));
		status.setSTATUS_NOME(rs.getString("STATUS_NOME"));
		return status;
	}

	public int recuperarStatusIdPorNome(String nomeDoStatus)
			throws SQLException, ClassNotFoundException {
		ResultSet rs = getStatement().executeQuery(
				"Select STATUS_ID from STATUS where STATUS_NOME="
						+nomeDoStatus);
		rs.next();
		return rs.getInt("STATUS_ID");
	}

}
