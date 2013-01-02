package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.TipoLaboratorio;

public class TipoLaboratorioDAO extends DatabaseUtil{
	
	public boolean adicionarTipoLaboratorio(TipoLaboratorio tipoLaboratorio) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		
		PreparedStatement ps = getPreparedStatement("Insert into TIPO_LABORATORIO set TIPO_LABORATORIO_NOME=?");
		ps.setString(1, tipoLaboratorio.getTIPO_LABORATORIO_NOME());
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
	}
	
	public void editarTipoLaboratorio(TipoLaboratorio tipoLaboratorio) throws ClassNotFoundException, SQLException
	{	
		PreparedStatement ps = getPreparedStatement("Update TIPO_LABORATORIO set TIPO_LABORATORIO_NOME=?");
		ps.setString(1, tipoLaboratorio.getTIPO_LABORATORIO_NOME());
		ps.execute();
		ps.close();
	}
	
	public List<TipoLaboratorio> listarTipoLaboratorio() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from TIPO_LABORATORIO");
		List<TipoLaboratorio> listaTipoLaboratorio = new LinkedList<TipoLaboratorio>();
		
		while(rs.next())
		{
			TipoLaboratorio tipoLaboratorio = new TipoLaboratorio();
			tipoLaboratorio = popularTipoLaboratorio(rs);
			listaTipoLaboratorio.add(tipoLaboratorio);
		}
		
		return listaTipoLaboratorio;
	}
	
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
