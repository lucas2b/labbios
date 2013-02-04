package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.GrupoExame;

public class GrupoExameDAO extends DatabaseUtil{
	
	public boolean adicionarGrupoExame(GrupoExame grupoExame) throws ClassNotFoundException, SQLException
	{		
		PreparedStatement ps = getPreparedStatement("Insert into GRUPO_EXAME set GRUPO_EXAME_NOME=?");
		//ps.setString(1, grupoExame.getGRUPO_EXAME_ABREV());
		ps.setString(1, grupoExame.getGRUPO_EXAME_NOME());
		
		int retorno = ps.executeUpdate();
		ps.close();
		
		return retorno > 0;
	}
	
	public void editarGrupoExame(GrupoExame grupoExame) throws ClassNotFoundException, SQLException
	{	
		PreparedStatement ps = getPreparedStatement("Update GRUPO_EXAME set GRUPO_EXAME_NOME=? where GRUPO_EXAME_ID=?");
		//ps.setString(1, grupoExame.getGRUPO_EXAME_ABREV());
		ps.setString(1, grupoExame.getGRUPO_EXAME_NOME());
		ps.setInt(3, grupoExame.getGRUPO_EXAME_ID());
	
		ps.execute();
	}
	
	public List<GrupoExame> listarGrupoExames() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from GRUPO_EXAME");
		List<GrupoExame> listaGrupoExame = new LinkedList<GrupoExame>();
		while(rs.next())
		{
			GrupoExame grupoExame = new GrupoExame();
			grupoExame = popularGrupoExame(rs);
			listaGrupoExame.add(grupoExame);
			//listaGrupoExame.add(popularGrupoExame(rs));
		}
		
		rs.close();
		
		return listaGrupoExame;
	}
	
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
		grupoExameRetorno.setGRUPO_EXAME_ID(rs.getInt("GRUPO_EXAME_ID"));
		//grupoExameRetorno.setGRUPO_EXAME_ABREV(rs.getString("GRUPO_EXAME_ABREV"));
		grupoExameRetorno.setGRUPO_EXAME_NOME(rs.getString("GRUPO_EXAME_NOME"));
		return grupoExameRetorno;
		
	}

}
