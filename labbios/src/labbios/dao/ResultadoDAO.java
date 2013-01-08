package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.Exame;
import labbios.dto.Resultado;

public class ResultadoDAO extends DatabaseUtil{
	
	ExameDAO exameDAO = new ExameDAO();
	
	
	public boolean verificaEntradaExistente(Exame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		/*
		 * executa QUERY em cima da tabela RESULTADO procurando pelo ID do exame selecionado
		 */
		
		ResultSet rs = getStatement().executeQuery("Select * from RESULTADO where EXAME_ID="+exameSelecionado.getEXAME_ID());
		
		if(rs.next())
			return true;
		else
			return false;
	}
	
	public boolean inserirNovoResultado(Resultado resultado) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps = getPreparedStatement("Insert into RESULTADO set RESULT_VALOR_ENCONTRADO=?," +
																				" RESULT_PARAMETRO=?," +
																				" RESULT_REFERENCIA=?," +
																				" RESULT_UNIDADE=?," +
																				" RESULT_OBSERVACOES=?," +
																				" EXAME_ID=?");
		
		ps.setString(1, resultado.getRESULT_VALOR_ENCONTRADO());
		ps.setString(2, resultado.getRESULT_PARAMETRO());
		
		
		return false;
	}
	
	
	public boolean updateResultadoExistente(List<Resultado> listaDeResultados)
	{
		return false;
	}
	
	public Resultado populaResultado(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		Resultado resultado = new Resultado();
		resultado.setRESULT_ID(rs.getInt("RESULT_ID"));
		resultado.setRESULT_OBSERVACOES(rs.getString("RESULT_OBSERVACOES"));
		resultado.setRESULT_PARAMETRO(rs.getString("RESULT_PARAMETRO"));
		resultado.setRESULT_UNIDADE(rs.getString("RESULT_UNIDADE"));
		resultado.setRESULT_VALOR_ENCONTRADO(rs.getString("RESULT_VALOR_ENCONTRADO"));
		resultado.setRESULT_VALOR_REFERENCIA(rs.getString("RESULT_REFERENCIA"));
		resultado.setEXAME(exameDAO.buscarExamePorID(rs.getInt("EXAME_ID")));
		return resultado;
	}
	
	
	public List<Resultado> recuperarResultado(Exame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		ResultSet rs = getStatement().executeQuery("Select * from RESULTADO where EXAME_ID="+exameSelecionado.getCAD_EXAME().getCAD_EXAME_ID());
		List<Resultado> listaDeResultados = new LinkedList<Resultado>();
		
		while(rs.next())
		{
			listaDeResultados.add(populaResultado(rs));
		}
		
		return listaDeResultados;
		
	}

}
