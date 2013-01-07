package labbios.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.dao.ResultadoDAO;
import labbios.dto.Exame;
import labbios.dto.Resultado;

public class EntradaDeResultadosBean {
	
	private Exame exameSelecionado;
	private boolean flagNovaEntrada;
	
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	private List<Resultado> listaResultado;
	
	
	//Função de início
	public String inicioEntradaResultado(Exame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		if(resultadoDAO.verificaEntradaExistente(exameSelecionado) != null)
		{
			
			
			
			if(resultadoDAO.verificaHemograma(exameSelecionado))
				return "resultadoTipoHemograma";
				//chama tela própria do hemograma
		}
		else
		{
			
			if(resultadoDAO.verificaHemograma(exameSelecionado))
				return "resultadoTipoHemograma";
				//chama tela própria do hemograma
		}
	}
	
	public String gravarEntradaDeResultados()
	{
		if(flagNovaEntrada)
		{
			//Chamar INSERT
		}
		else
		{
			//Chamar UPDATE
		}
	}

}
