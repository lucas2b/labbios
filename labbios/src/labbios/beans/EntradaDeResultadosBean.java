package labbios.beans;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import labbios.dao.DadosDoExameDAO;
import labbios.dao.ResultadoDAO;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.Exame;
import labbios.dto.Resultado;


/*
 * Autor: Lucas Bonine
 * 
 * Descrição de funcionamento do mecanismo de gravação de resultados na tabela RESULTADOS:
 * 
 * Caso 1 - Gravação de novos resultados (INSERT):
 * Ao iniciar a grade de entrada de resultados, a mesma será populada com um molde
 * da tabela previamente criada com os dados característicos de cada exame. Nesta
 * grade conterão não apenas estes dados, mas também os dados inseridos pelo usuário,
 * ou seja os resultados do exame. As colunas de dados dos resultados serão armazenadas
 * em outra lista, que não seja a lista de molde (listaDeResultado). No momento de inserção
 * dos resultados a listaDeResultado adicionará um a um os parâmetros do molde e também
 * os dados de entrada do resultado, mixando em ordem dados do molde e de entrada do usuário.
 * 
 * Caso 1 - Update de resultados existentes:
 * Neste caso, o resultado e os dados de molde já estão compostos em uma lista previamente
 * criada e adicionada ao banco (INSERT), então basta fazer um update dos ID's do exame
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
	
	
	//Listas de auxílio
	private List<Resultado> listaDeResultado; //Lista de inserção recuperada à partir do molde + entradas do resultado
	private List<DadosDoExameSuporte> tabelaMolde; //Molde da tabela de exames recuperado
	
	
	//Função de início
	public String inicioEntradaResultado() throws SQLException, ClassNotFoundException
	{
		
		if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
		{
			//UPDATE
			flagNovaEntrada = false;
			listaDeResultado = resultadoDAO.recuperarResultado(exameSelecionado);
			
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
			
			Iterator<Resultado> iteradorResultados = listaDeResultado.iterator();
			Iterator<DadosDoExameSuporte> iteradorMolde = tabelaMolde.iterator();
			
			Resultado resultadoComposto = new Resultado();
			
			while(iteradorResultados.hasNext() && iteradorMolde.hasNext())
			{
				resultadoComposto.setEXAME(exameSelecionado);
				
				resultadoComposto.setRESULT_OBSERVACOES(((Resultado)iteradorResultados.next()).getRESULT_OBSERVACOES()); //virá da tela
				resultadoComposto.setRESULT_VALOR_ENCONTRADO(((Resultado)iteradorResultados.next()).getRESULT_VALOR_ENCONTRADO()); //virá da tela
				
				
				//Estes virão do iteradorMolde
				resultadoComposto.setRESULT_PARAMETRO( ((DadosDoExameSuporte)iteradorMolde.next()).getParametro() );
				resultadoComposto.setRESULT_UNIDADE( ((DadosDoExameSuporte)iteradorMolde.next()).getUnidade() );
				resultadoComposto.setRESULT_VALOR_REFERENCIA( String.valueOf(((DadosDoExameSuporte)iteradorMolde.next()).getReferencia()) );
				
				resultadoDAO.inserirNovoResultado(resultadoComposto);
				
			}
		}
		else
		{
			//Chamar UPDATE
		}
		
		return "refresh";
	}
	
	
	
	//GETTERS AND SETTERS

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}

}
