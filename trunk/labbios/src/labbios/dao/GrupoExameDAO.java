package labbios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import labbios.db.DatabaseUtil;
import labbios.db.GrupoExame;

public class GrupoExameDAO extends DatabaseUtil{
	
	public GrupoExame buscarGrupoExamePorID(int grupoExameID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from GRUPO_EXAME where GRUPO_EXAME_ID="+grupoExameID);
		GrupoExame grupoExameRetorno = null;
		if(rs.next())
		{
			grupoExameRetorno = popularGrupoExame(rs);
		}
		
		return grupoExameRetorno;
	}

	private GrupoExame popularGrupoExame(ResultSet rs) throws SQLException {
		GrupoExame grupoExameRetorno = new GrupoExame();
		grupoExameRetorno.setGRUPO_EXAME_ABREV(rs.getString("GRUPO_EXAME_ABREV"));
		grupoExameRetorno.setGRUPO_EXAME_NOME(rs.getString("GRUPO_EXAME_NOME"));
		return grupoExameRetorno;
		
	}

}
