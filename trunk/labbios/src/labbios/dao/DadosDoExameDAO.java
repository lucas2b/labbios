package labbios.dao;

import labbios.db.DatabaseUtil;
import labbios.dto.DadosDoExame;
import labbios.dto.CadastroDeExame;

public class DadosDoExameDAO extends DatabaseUtil{
	
	private String parametro;
	private String unidade;
	private int referencia;
	private CadastroDeExame exame;
	
	public boolean adicionarDadosDoExame(DadosDoExame dadosDoExame)
	{
		boolean retorno = false;
		return retorno;
	}

}
