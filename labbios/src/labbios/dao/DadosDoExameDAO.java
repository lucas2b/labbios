package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.CadastroDeExame;
import labbios.dto.DadosDeExameGrade;

public class DadosDoExameDAO extends DatabaseUtil{
	
	CadastroDeExameDAO cadExameDAO = new CadastroDeExameDAO();
	
	public boolean verificarExistenciaDeTabela(CadastroDeExame exameSelecionado) throws ClassNotFoundException, SQLException
	{
//		PreparedStatement ps = getPreparedStatement("Show tables like ?");
//		ps.setString(1, "DADOS_DO_EXAME_ID"+exameSelecionado.getCAD_EXAME_ID());
//		
//		ResultSet rs = ps.executeQuery();
//		
//		if(rs.next())
//			return true;
//		else
//			return false;
		
		ResultSet rsGrade = getStatement().executeQuery("Select * from DADOS_DE_EXAME_GRADE where CAD_EXAME_ID="+exameSelecionado.getCAD_EXAME_ID());
		ResultSet rsTexto = getStatement().executeQuery("Select * from DADOS_DE_EXAME_TEXTO where CAD_EXAME_ID="+exameSelecionado.getCAD_EXAME_ID());
		
		if(rsGrade.next() || rsTexto.next())
			return true;
		else
			return false;
	}
	
	public List<DadosDeExameGrade> recuperarTabela(CadastroDeExame exameSelecionado) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from DADOS_DE_EXAME_GRADE where CAD_EXAME_ID="+exameSelecionado.getCAD_EXAME_ID());
		List<DadosDeExameGrade> listaDadosDoExame = new LinkedList<DadosDeExameGrade>();
		
		while(rs.next())
		{
			DadosDeExameGrade dadosDeExameGrade = new DadosDeExameGrade();
			dadosDeExameGrade.setCAD_EXAME_ID(cadExameDAO.buscarCadastroDeExamePorID(rs.getInt("CAD_EXAME_ID")));
			dadosDeExameGrade.setDADOS_GRADE_PARAMETRO(rs.getString("DADOS_GRADE_PARAMETRO"));
			dadosDeExameGrade.setDADOS_GRADE_REFERENCIA(rs.getDouble("DADOS_GRADE_REFERENCIA"));
			dadosDeExameGrade.setDADOS_GRADE_UNIDADE(rs.getString("DADOS_GRADE_UNIDADE"));
			listaDadosDoExame.add(dadosDeExameGrade);
		}
		
		return listaDadosDoExame;
	}
	
	public String recuperarTexto(CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		ResultSet rs = getStatement().executeQuery("Select * from DADOS_DE_EXAME_TEXTO where CAD_EXAME_ID="+exameSelecionado.getCAD_EXAME_ID());
		rs.next();
		return rs.getString("DADOS_TEXTO_TEXTO");
	}
	
	
	public void adicionarDadosDoExameEmGrade(List<DadosDeExameGrade> listaSuporte, CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{	
		PreparedStatement ps = getPreparedStatement("Insert into DADOS_DE_EXAME_GRADE set DADOS_GRADE_PARAMETRO=?, DADOS_GRADE_REFERENCIA=?, DADOS_GRADE_UNIDADE=?, CAD_EXAME_ID=?");
		
		for(DadosDeExameGrade item : listaSuporte)
		{
			if( (item.getDADOS_GRADE_PARAMETRO() != null) || (item.getDADOS_GRADE_REFERENCIA() != 0) || (item.getDADOS_GRADE_UNIDADE() != null) )
			{				
				ps.setString(1, item.getDADOS_GRADE_PARAMETRO());
				ps.setDouble(2, item.getDADOS_GRADE_REFERENCIA());
				ps.setString(3, item.getDADOS_GRADE_UNIDADE());
				ps.setInt(4, exameSelecionado.getCAD_EXAME_ID());
				ps.execute();
			}
		}
		
	}
	
	public void atualizarDadosDoExameEmGrade(List<DadosDeExameGrade> listaSuporte, CadastroDeExame exameSelecionado) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Update DADOS_DE_EXAME_GRADE set DADOS_GRADE_ID=?, DADOS_GRADE_PARAMETRO=?, DADOS_GRADE_REFERENCIA=?, DADOS_GRADE_UNIDADE=? where CAD_EXAME_ID=?");
		
		for(DadosDeExameGrade item : listaSuporte)
		{
			if( (item.getDADOS_GRADE_PARAMETRO() != null) || (item.getDADOS_GRADE_UNIDADE() != null) )
			{				
				ps.setString(1, item.getDADOS_GRADE_PARAMETRO());
				ps.setDouble(2, item.getDADOS_GRADE_REFERENCIA());
				ps.setString(3, item.getDADOS_GRADE_UNIDADE());
				ps.setInt(4, exameSelecionado.getCAD_EXAME_ID());
				ps.execute();
			}
		}
		
	}
	
	public void adicionarDadosDoExameEmTexto(String texto, CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps = getPreparedStatement("Insert into DADOS_DE_EXAME_TEXTO set DADOS_TEXTO_TEXTO=?, CAD_EXAME_ID=?");
		ps.setString(1, texto);
		ps.setInt(2, exameSelecionado.getCAD_EXAME_ID());
	}
	
	public void editarDadosDoExameEmTexto(String texto, CadastroDeExame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps = getPreparedStatement("Update DADOS_DE_EXAME_TEXTO set DADOS_TEXTO_TEXTO=? where DADOS_TEXTO_ID=?");
		ps.setString(1, texto);
		ps.setInt(2, exameSelecionado.getCAD_EXAME_ID());
		ps.execute();
	}
	
	//PARTE DE ENTRADA DE RESULTADOS
	

}
