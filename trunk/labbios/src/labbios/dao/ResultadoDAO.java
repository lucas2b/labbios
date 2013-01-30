package labbios.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.Exame;
import labbios.dto.Paciente;
import labbios.dto.Resultado;
import labbios.dto.Solicitacao;
import labbios.dto.TabelaPrecos;

public class ResultadoDAO extends DatabaseUtil{
	
	ExameDAO exameDAO = new ExameDAO();
	TabelaPrecosDAO tabelaDePrecosDAO = new TabelaPrecosDAO();
	SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	PacienteDAO pacienteDAO = new PacienteDAO();
	MedicoDAO medicoDAO = new MedicoDAO();
	
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
	
	public boolean inserirNovoResultado(List<Resultado> listaResultadosParam) throws SQLException, ClassNotFoundException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into RESULTADO set RESULT_VALOR_ENCONTRADO=?," +
																				" RESULT_PARAMETRO=?," +
																				" RESULT_REFERENCIA=?," +
																				" RESULT_UNIDADE=?," +
																				" RESULT_OBSERVACOES=?," +
																				" EXAME_ID=?");
		
		for(Resultado listaResultados : listaResultadosParam)
		{
			ps.setDouble(1, listaResultados.getRESULT_VALOR_ENCONTRADO());
			ps.setString(2, listaResultados.getRESULT_PARAMETRO());
			ps.setString(3, listaResultados.getRESULT_VALOR_REFERENCIA());
			ps.setString(4, listaResultados.getRESULT_UNIDADE());
			ps.setString(5, listaResultados.getRESULT_OBSERVACOES());
			ps.setInt(6, listaResultados.getEXAME().getEXAME_ID());
			
			retorno = ps.execute();
		}
		
		return retorno;
	}
	
	
	public boolean updateResultadoExistente(List<Resultado> listaResultadosParam) throws SQLException, ClassNotFoundException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Update RESULTADO set RESULT_VALOR_ENCONTRADO=?," +
																				" RESULT_PARAMETRO=?," +
																				" RESULT_REFERENCIA=?," +
																				" RESULT_UNIDADE=?," +
																				" RESULT_OBSERVACOES=?," +
																				" EXAME_ID=? where RESULT_ID=?");
		
		for(Resultado listaResultados : listaResultadosParam)
		{
			ps.setDouble(1, listaResultados.getRESULT_VALOR_ENCONTRADO());
			ps.setString(2, listaResultados.getRESULT_PARAMETRO());
			ps.setString(3, listaResultados.getRESULT_VALOR_REFERENCIA());
			ps.setString(4, listaResultados.getRESULT_UNIDADE());
			ps.setString(5, listaResultados.getRESULT_OBSERVACOES());
			ps.setInt(6, listaResultados.getEXAME().getEXAME_ID());
			ps.setInt(7, listaResultados.getRESULT_ID());
			
			retorno = ps.execute();
		}
		
		return retorno;
	}
	
	public Resultado populaResultado(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		Resultado resultado = new Resultado();
		resultado.setRESULT_ID(rs.getInt("RESULT_ID"));
		resultado.setRESULT_OBSERVACOES(rs.getString("RESULT_OBSERVACOES"));
		resultado.setRESULT_PARAMETRO(rs.getString("RESULT_PARAMETRO"));
		resultado.setRESULT_UNIDADE(rs.getString("RESULT_UNIDADE"));
		resultado.setRESULT_VALOR_ENCONTRADO(rs.getDouble("RESULT_VALOR_ENCONTRADO"));
		resultado.setRESULT_VALOR_REFERENCIA(rs.getString("RESULT_REFERENCIA"));
		resultado.setEXAME(exameDAO.buscarExamePorID(rs.getInt("EXAME_ID")));
		return resultado;
	}
	
	
	public List<Resultado> recuperarResultado(Exame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		ResultSet rs = getStatement().executeQuery("Select * from RESULTADO where EXAME_ID="+exameSelecionado.getEXAME_ID());
		List<Resultado> listaDeResultados = new LinkedList<Resultado>();
		
		while(rs.next())
		{
			listaDeResultados.add(populaResultado(rs));
		}
		
		return listaDeResultados;
	}
	
	public List<String> cabecalhoDeExame(Exame exameSelecionado) throws NumberFormatException, SQLException, ClassNotFoundException
	{
		ResultSet rs = getStatement().executeQuery("Select * from EXAME where EXAME_ID="+exameSelecionado.getEXAME_ID());
		
		if(rs.next())
		{
		
			//Trazendo o objeto Solicitacao à partir de seu ID
			int solicitacaoID = rs.getInt("SOL_ID");
			Solicitacao solicitacao = solicitacaoDAO.procurarSolicitacaoPorID(solicitacaoID);
			
			//Atributos de MEDICO
			String medico = solicitacao.getMEDICO().getMEDICO_NOME();
			String crm = solicitacao.getMEDICO().getMEDICO_CRM();
			
			//Atributos de PACIENTE
			Paciente paciente = solicitacao.getPACIENTE(); //Trazendo objeto Paciente
			String nomePaciente = paciente.getPACIENTE_NOME();
			int codigoDePaciente = paciente.getPACIENTE_ID();
			Date dataDeNascimento = paciente.getPACIENTE_DT_NASCIMENTO();
			
			//Atributos de EXAME
			Exame exame = exameDAO.buscarExamePorID(exameSelecionado.getEXAME_ID());
			String nomeDoExame = exame.getCAD_EXAME().getCAD_EXAME_NOME();
			Date dataDeRealizacao = exame.getEXAME_DT_REALIZACAO();
			
			//CONVENIO
			String convenio = exame.getEXAME_VALOR().getCONVENIO().getCONVENIO_NOME();
			
			//Montando a lista de retorno
			List<String> retornoDeCabecalho = new LinkedList<String>();
			retornoDeCabecalho.add(nomeDoExame);//Nome do exame
			retornoDeCabecalho.add(nomePaciente); //Nome do Paciente
			retornoDeCabecalho.add(String.valueOf(codigoDePaciente)); //Código do Paciente
			
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); //Formatador de datas
			retornoDeCabecalho.add(df.format(dataDeNascimento)); //Data de nascimento
			retornoDeCabecalho.add(df.format(dataDeRealizacao)); //Data de realização do exame
			retornoDeCabecalho.add(medico); //Medico
			retornoDeCabecalho.add(crm); //CRM
			retornoDeCabecalho.add(convenio); //Convenio
			
			return retornoDeCabecalho;
		
		}
		else
		{
			return null;
		}
		
		
	}

}
