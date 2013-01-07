package labbios.beans;

import java.sql.SQLException;

import labbios.dao.ResultadoDAO;
import labbios.dto.Exame;

public class EntradaDeResultadosBean {
	
	private Exame exameSelecionado;
	private boolean flagNovaEntrada;
	
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	
	
	//Determina insert ou update
	public String inicioEntradaResultado(Exame exameSelecionado) throws SQLException, ClassNotFoundException
	{
		if(resultadoDAO.verificaEntradaExistente(exameSelecionado) != null)
		{
			//Caso de entrada de resultados j� existente, prosseguir com um UPDATE
			
			
			if(resultadoDAO.verificaHemograma(exameSelecionado))
				return "resultadoTipoHemograma";
				//chama tela pr�pria do hemograma
		}
		else
		{
			//Caso de entrada de resultados ainda n�o existente, prosseguir com um INSERT
			
			if(resultadoDAO.verificaHemograma(exameSelecionado))
				return "resultadoTipoHemograma";
				//chama tela pr�pria do hemograma
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
