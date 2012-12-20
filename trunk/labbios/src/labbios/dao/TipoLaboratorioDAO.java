package labbios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import labbios.db.DatabaseUtil;
import labbios.db.TipoLaboratorio;

public class TipoLaboratorioDAO extends DatabaseUtil{
	
	public TipoLaboratorio buscarTipoLaboratorioPorID(int tipoLaboratorioID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from TIPO_LABORATORIO where TIPO_LABORATORIO_ID="+tipoLaboratorioID);
		TipoLaboratorio tipoLaboratorioRetorno = null;
		
		if(rs.next())
		{
			tipoLaboratorioRetorno = popularTipoLaboratorio(rs);
		}
		
		return tipoLaboratorioRetorno;
	}

	private TipoLaboratorio popularTipoLaboratorio(ResultSet rs) throws SQLException {
		TipoLaboratorio tipoLaboratorioRetorno = new TipoLaboratorio();
		tipoLaboratorioRetorno.setTIPO_LABORATORIO_ID(rs.getInt("TIPO_LABORATORIO_ID"));
		tipoLaboratorioRetorno.setTIPO_LABORATORIO_NOME(rs.getString("TIPO_LABORATORIO_NOME"));
		return tipoLaboratorioRetorno;
	}

}
