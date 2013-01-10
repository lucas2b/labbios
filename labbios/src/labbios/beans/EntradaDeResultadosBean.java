package labbios.beans;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import labbios.dao.DadosDoExameDAO;
import labbios.dao.ExameDAO;
import labbios.dao.ResultadoDAO;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.Exame;
import labbios.dto.Resultado;


/*
 * Autor: Lucas Bonine
 * 
 * Descri��o de funcionamento do mecanismo de grava��o de resultados na tabela RESULTADOS:
 * 
 * Caso 1 - Grava��o de novos resultados (INSERT):
 * Ao iniciar a grade de entrada de resultados, a mesma ser� populada com um molde
 * da tabela previamente criada com os dados caracter�sticos de cada exame. Nesta
 * grade conter�o n�o apenas estes dados, mas tamb�m os dados inseridos pelo usu�rio,
 * ou seja os resultados do exame. As colunas de dados dos resultados ser�o armazenadas
 * em outra lista, que n�o seja a lista de molde (listaDeResultado). No momento de inser��o
 * dos resultados a listaDeResultado adicionar� um a um os par�metros do molde e tamb�m
 * os dados de entrada do resultado, mixando em ordem dados do molde e de entrada do usu�rio.
 * 
 * Caso 1 - Update de resultados existentes:
 * Neste caso, o resultado e os dados de molde j� est�o compostos em uma lista previamente
 * criada e adicionada ao banco (INSERT), ent�o basta fazer um update dos ID's do exame
 * com a lista alterada vinda da grade
 * 
 */

public class EntradaDeResultadosBean {
	
	//Atributos de controle
	private Exame exameSelecionado;
	private boolean flagNovaEntrada = false;
	
	//DAOS usados
	private DadosDoExameDAO dadosDoExameDAO = new DadosDoExameDAO();
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	private ExameDAO exameDAO = new ExameDAO();
	
	
	//Listas de aux�lio
	private List<Resultado> listaDeResultado; //Lista de inser��o recuperada � partir do molde + entradas do resultado
	private List<Resultado> listaSuporte;
	private List<DadosDoExameSuporte> tabelaMolde; //Molde da tabela de exames recuperado
	
	
	//Fun��o de in�cio
	public String inicioEntradaResultado() throws SQLException, ClassNotFoundException
	{
		
		if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
		{
			//UPDATE
			flagNovaEntrada = false;
			listaSuporte = resultadoDAO.recuperarResultado(exameSelecionado);
			
			if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().contains("HEMO"))
				return "resultadoTipoHemograma";
			 else
				return "entradaDeResultadosTipoComum";
			
		}
		else
		{
			//INSERT
			flagNovaEntrada = true;
			
			tabelaMolde = dadosDoExameDAO.recuperarTabela(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME());
			listaSuporte = new LinkedList<Resultado>();
			for(DadosDoExameSuporte molde: tabelaMolde)
			{
				Resultado resultado = new Resultado();
				resultado.setEXAME(exameDAO.buscarExamePorID(exameSelecionado.getEXAME_ID()));
				resultado.setRESULT_PARAMETRO(molde.getParametro());
				resultado.setRESULT_UNIDADE(molde.getUnidade());
				resultado.setRESULT_VALOR_REFERENCIA(molde.getReferencia());
				resultado.setRESULT_VALOR_ENCONTRADO("");
				resultado.setRESULT_OBSERVACOES("");
				listaSuporte.add(resultado);
			}
			
			
			if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().contains("HEMO"))
				return "resultadoTipoHemograma";
			 else
				 return "entradaDeResultadosTipoComum";
		}
		
	}
	
	public String botaoGravarResultados() throws SQLException, ClassNotFoundException
	{
		if(flagNovaEntrada)
		{
			//Chamar INSERT
			
			resultadoDAO.inserirNovoResultado(listaSuporte);
		}
		else
		{
			//Chamar UPDATE
			
			resultadoDAO.updateResultadoExistente(listaSuporte);
		}
		
		return "refresh";
	}
	
	public String retornarParaSolicitacao()
	{
		return "editarSolicitacao";
	}
	
	
	
	//GETTERS AND SETTERS

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}

	public List<Resultado> getListaDeResultado() {
		return listaDeResultado;
	}

	public void setListaDeResultado(List<Resultado> listaDeResultado) {
		this.listaDeResultado = listaDeResultado;
	}

	public List<DadosDoExameSuporte> getTabelaMolde() {
		return tabelaMolde;
	}

	public void setTabelaMolde(List<DadosDoExameSuporte> tabelaMolde) {
		this.tabelaMolde = tabelaMolde;
	}

	public List<Resultado> getListaSuporte() {
		return listaSuporte;
	}

	public void setListaSuporte(List<Resultado> listaSuporte) {
		this.listaSuporte = listaSuporte;
	}

}
