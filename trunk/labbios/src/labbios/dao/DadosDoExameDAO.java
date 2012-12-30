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
		PreparedStatement ps = getPreparedStatement("Select * from ?");
		ps.setString(1, exameNome);
		ResultSet rs = ps.executeQuery(); 
		
		if(rs.next())
			return true;
		else
			return false;
	}
	
	public List<DadosDoExameSuporte> recuperarTabela(String exameNome) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from"+exameNome);
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
		
		PreparedStatement ps = getPreparedStatement("CREATE TABLE ? (PARAMETRO VARCHAR(255), REFERENCIA VARCHAR(255), UNIDADE INT(11) )");
		ps.setString(1, exameNome);
		
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			PreparedStatement psInsert = getPreparedStatement("Insert into ? set PARAMETRO=?, REFERENCIA=?, UNIDADE=?");
			psInsert.setString(1, exameNome);
			psInsert.setString(2, dado.getParametro());
			psInsert.setInt(3, dado.getReferencia());
			psInsert.setString(4, dado.getUnidade());
			
			retorno =psInsert.execute();
		}
		
		return retorno;
	
	}
	
	public boolean atualizarDadosDoExame(List<DadosDoExameSuporte> listaSuporte, String exameNome) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			PreparedStatement psInsert = getPreparedStatement("Update ? set PARAMETRO=?, REFERENCIA=?, UNIDADE=?");
			psInsert.setString(1, exameNome);
			psInsert.setString(2, dado.getParametro());
			psInsert.setInt(3, dado.getReferencia());
			psInsert.setString(4, dado.getUnidade());
			
			retorno =psInsert.execute();
		}
		
		return retorno;
	}

}
