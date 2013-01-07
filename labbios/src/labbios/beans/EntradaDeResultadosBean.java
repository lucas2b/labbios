package labbios.beans;

import labbios.dto.Exame;

public class EntradaDeResultadosBean {
	
	private Exame exameSelecionado;
	private boolean flagNovaEntrada;
	
	
	public String inicioEntradaResultado(Exame exameSelecionado)
	{
		if( --Chamada do ResultadoDAO que verifica se trata-se de uma nova entrada ou edição de existente --)
		{
			//Caso de entrada de resultados já existente, prosseguir com um UPDATE 
		}
		else
		{
			//Caso de entrada de resultados ainda não existente, prosseguir com um INSERT
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
