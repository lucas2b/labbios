package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.DadosDoExameSuporte;

public class DadosDoExameDAO extends DatabaseUtil{
	
	public boolean verificarExistenciaDeTabela(String exameNome) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Show tables like ?");
		ps.setString(1, exameNome);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
			return true;
		else
			return false;
	}
	
	public List<DadosDoExameSuporte> recuperarTabela(String exameNome) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from "+exameNome);
		List<DadosDoExameSuporte> listaDadosDoExame = new LinkedList<DadosDoExameSuporte>();
		
		while(rs.next())
		{
			DadosDoExameSuporte dadosDoExameSuporte = new DadosDoExameSuporte();
			dadosDoExameSuporte.setParametro(rs.getString("PARAMETRO"));
			dadosDoExameSuporte.setReferencia(rs.getInt("REFERENCIA"));
			dadosDoExameSuporte.setUnidade(rs.getString("UNIDADE"));
			listaDadosDoExame.add(dadosDoExameSuporte);
		}
		
		return listaDadosDoExame;
	}
	
	
	public boolean adicionarDadosDoExame(List<DadosDoExameSuporte> listaSuporte, String exameNome) throws SQLException, ClassNotFoundException
	{
		boolean retorno = false;
		
		getStatement().executeUpdate("Create table " +exameNome+ " (ID int AUTO_INCREMENT PRIMARY KEY, PARAMETRO varchar(255), REFERENCIA int, UNIDADE varchar(255) ) ");
		
		
		
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			
			
			if(dado.getParametro() == null || dado.getReferencia() == 0 || dado.getUnidade() == null)
			{
				
			}
			else
			{
				getStatement().executeUpdate("Insert into " +exameNome+ " set PARAMETRO='" +dado.getParametro()+ "', REFERENCIA='" +dado.getReferencia()+"', UNIDADE='"+dado.getUnidade()+"'");				
			}
		}
		
		return retorno;
	
	}
	
	public boolean atualizarDadosDoExame(List<DadosDoExameSuporte> listaSuporte, String exameNome) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			int i=1;
			getStatement().executeUpdate("Update " +exameNome+ " set PARAMETRO='" +dado.getParametro()+ "', REFERENCIA='" +dado.getReferencia()+"', UNIDADE='"+dado.getUnidade()+"' where ID="+i);
			i++;
		}
		
		return retorno;
	}

}
