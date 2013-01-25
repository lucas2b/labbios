package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import labbios.db.DatabaseUtil;
import labbios.dto.CadastroDeExame;
import labbios.dto.DadosDoExameSuporte;

public class DadosDoExameDAO extends DatabaseUtil{
	
	CadastroDeExameDAO cadExameDAO = new CadastroDeExameDAO();
	
	public boolean verificarExistenciaDeTabela(CadastroDeExame exameSelecionado) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Show tables like ?");
		ps.setString(1, "DADOS_DO_EXAME_ID"+exameSelecionado.getCAD_EXAME_ID());
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
			return true;
		else
			return false;
	}
	
	public List<DadosDoExameSuporte> recuperarTabela(CadastroDeExame exameSelecionado) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from DADOS_DO_EXAME_ID"+exameSelecionado.getCAD_EXAME_ID());
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
	
	public String recuperarTexto(CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		ResultSet rs = getStatement().executeQuery("Select * from DADOS_DO_EXAME_ID"+exameSelecionado.getCAD_EXAME_ID());
		rs.next();
		return rs.getString("TEXTO");
	}
	
	
	public void adicionarDadosDoExameEmGrade(List<DadosDoExameSuporte> listaSuporte, CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{	
		getStatement().executeUpdate("Create table DADOS_DO_EXAME_ID" +exameSelecionado.getCAD_EXAME_ID()+ " (ID int AUTO_INCREMENT PRIMARY KEY, PARAMETRO varchar(255), REFERENCIA varchar(255), UNIDADE varchar(255) ) ");
		
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			
			
			if(dado.getParametro() == null || dado.getReferencia() == null || dado.getUnidade() == null)
			{
				
			}
			else
			{
				getStatement().executeUpdate("Insert into DADOS_DO_EXAME_ID" +exameSelecionado.getCAD_EXAME_ID()+" set PARAMETRO='" +dado.getParametro()+ "', REFERENCIA='" +dado.getReferencia()+"', UNIDADE='"+dado.getUnidade()+"'");				
			}
		}
		
	
	}
	
	public void atualizarDadosDoExameEmGrade(List<DadosDoExameSuporte> listaSuporte, CadastroDeExame exameSelecionado) throws ClassNotFoundException, SQLException
	{
	
		int i=1;
		
		for(DadosDoExameSuporte dado : listaSuporte)
		{
			getStatement().executeUpdate("Update DADOS_DO_EXAME_ID"+exameSelecionado.getCAD_EXAME_ID()+" set PARAMETRO='" +dado.getParametro()+ "', REFERENCIA='" +dado.getReferencia()+"', UNIDADE='"+dado.getUnidade()+"' where ID="+i);
			i++;
		}
		
	}
	
	public void adicionarDadosDoExameEmTexto(String texto, CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		getStatement().executeUpdate("Create table DADOS_DO_EXAME_ID" +exameSelecionado.getCAD_EXAME_ID()+"(ID int AUTO_INCREMENT PRIMARY KEY, TEXTO text)");
		getStatement().executeUpdate("Insert into DADOS_DO_EXAME_ID" +exameSelecionado.getCAD_EXAME_ID()+" set TEXTO='" +texto+ "'"); 
	}
	
	public void editarDadosDoExameEmTexto(String texto, CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		getStatement().executeUpdate("Update  DADOS_DO_EXAME_ID"+exameSelecionado.getCAD_EXAME_ID()+" set TEXTO='" +texto+ "' where ID=1"); 
	}
	
	//PARTE DE ENTRADA DE RESULTADOS
	

}
