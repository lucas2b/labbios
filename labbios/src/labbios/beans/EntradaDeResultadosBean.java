package labbios.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.dao.DadosDoExameDAO;
import labbios.dao.ExameDAO;
import labbios.dao.ResultadoDAO;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.Exame;
import labbios.dto.Resultado;


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
		
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 )
		{
			//Caso em que o usu�rio adicionou o exame e tentou entrar resultado sem salvar solicita��o antes, retorna para listagem de solicita��es
			return retornarParaSolicitacao();
		}
		else
		{
			//Caso tudo OK, prossegue com a rotina de inicializa��o
		
					if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
					{
							//Resultado j� existente, procede com um Update
							flagNovaEntrada = false;
							listaSuporte = resultadoDAO.recuperarResultado(exameSelecionado);
							
							if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().contains("HEMO"))
								return "resultadoTipoHemograma";
							 else
								return "entradaDeResultadosTipoComum";
						
					}
					else
					{
							//Resultado n�o existente, procede com um Insert
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
							
							/*
							 * Descri��o do for acima:
							 * Popula uma lista de resultados que ser� jogada no grid, adiciona os campos vindos do molde de Resultado
							 * como o Parametro, Unidade e o valor de refer�ncia e dois valores em brancos para Valor Encontrado
							 * e Observa��es, para que os espa�os em branco no grid possam ser editados corretamente
 							 */
							
							
							if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().contains("HEMO"))
								return "resultadoTipoHemograma";
							 else
								 return "entradaDeResultadosTipoComum";
					}
					
		}
		
	}
	
	public String botaoGravarResultados() throws SQLException, ClassNotFoundException
	{
		if(flagNovaEntrada)
		{	
			resultadoDAO.inserirNovoResultado(listaSuporte);
		}
		else
		{	
			resultadoDAO.updateResultadoExistente(listaSuporte);
		}
		
		return retornarParaSolicitacao();
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
