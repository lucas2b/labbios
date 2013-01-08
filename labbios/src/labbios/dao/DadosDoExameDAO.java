package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.DadosDoExame;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.Exame;
import labbios.dto.Resultado;

public class DadosDoExameDAO extends DatabaseUtil{
	
	CadastroDeExameDAO cadExameDAO = new CadastroDeExameDAO();
	
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
			dadosDoExameSuporte.setReferencia(rs.getString("REFERENCIA"));
			dadosDoExameSuporte.setUnidade(rs.getString("UNIDADE"));
			listaDadosDoExame.add(dadosDoExameSuporte);
		}
		
		return listaDadosDoExame;
	}
	
	
	public void adicionarDadosDoExame(List<DadosDoExameSuporte> listaSuporte, String exameNome) throws SQLException, ClassNotFoundException
	{	
		getStatement().executeUpdate("Create table " +exameNome+ " (ID int AUTO_INCREMENT PRIMARY KEY, PARAMETRO varchar(255), REFERENCIA varchar(255), UNIDADE varchar(255) ) ");
		
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			
			
			if(dado.getParametro() == null || dado.getReferencia() == null || dado.getUnidade() == null)
			{
				
			}
			else
			{
				getStatement().executeUpdate("Insert into " +exameNome+ " set PARAMETRO='" +dado.getParametro()+ "', REFERENCIA='" +dado.getReferencia()+"', UNIDADE='"+dado.getUnidade()+"'");				
			}
		}
		
	
	}
	
	public void atualizarDadosDoExame(List<DadosDoExameSuporte> listaSuporte, String exameNome) throws ClassNotFoundException, SQLException
	{
	
		int i=1;
		
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			getStatement().executeUpdate("Update " +exameNome+ " set PARAMETRO='" +dado.getParametro()+ "', REFERENCIA='" +dado.getReferencia()+"', UNIDADE='"+dado.getUnidade()+"' where ID="+i);
			i++;
		}
		
	}
	
	//PARTE DE ENTRADA DE RESULTADOS
	

}
