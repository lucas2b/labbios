package labbios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.db.DatabaseUtil;
import labbios.dto.Medico;
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
	
	public List<SelectItem> getComboStatus() throws ClassNotFoundException, SQLException 
	{		
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		
		ResultSet rs = getStatement().executeQuery("Select * from STATUS");
		List<Status> listaStatus = new LinkedList<Status>();
		
		while(rs.next())
		{
			listaStatus.add(popularStatus(rs));
		}
		
		for(Status status : listaStatus)
		{
			toReturn.add(new SelectItem(status, status.getSTATUS_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;
	}

}
