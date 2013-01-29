package labbios.beans;


import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
	
	
	//Listas de auxílio
	private List<Resultado> listaDeResultado; //Lista de inserção recuperada à partir do molde + entradas do resultado
	private List<Resultado> listaDeExibicao;
	private List<DadosDoExameSuporte> tabelaMolde; //Molde da tabela de exames recuperado
	
	
	//Função de início
	public String inicioEntradaResultado() throws SQLException, ClassNotFoundException
	{
		
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"A solicitação ainda não foi salva! Clique em salvar e retorne para entrar resultados!", ""));
		}
		else
		{
		
					if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
					{
							//Update
							flagNovaEntrada = false;
							listaDeExibicao = resultadoDAO.recuperarResultado(exameSelecionado);
					}
					else
					{
							//Insert
							flagNovaEntrada = true;
							
							tabelaMolde = dadosDoExameDAO.recuperarTabela(exameSelecionado.getCAD_EXAME());
							listaDeExibicao = new LinkedList<Resultado>();
							
							for(DadosDoExameSuporte molde: tabelaMolde)
							{
								Resultado resultado = new Resultado();
								resultado.setEXAME(exameDAO.buscarExamePorID(exameSelecionado.getEXAME_ID()));
								resultado.setRESULT_PARAMETRO(molde.getParametro());
								resultado.setRESULT_UNIDADE(molde.getUnidade());
								resultado.setRESULT_VALOR_REFERENCIA(molde.getReferencia());
								resultado.setRESULT_VALOR_ENCONTRADO("");
								resultado.setRESULT_OBSERVACOES("");
								listaDeExibicao.add(resultado);
							}
							
					}
					
					
					
					if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().contains("HEMO"))
					{
						if(flagNovaEntrada)
							new EntradaDeHemograma(exameSelecionado, listaDeExibicao);
						else
							new EntradaDeHemograma(exameSelecionado);
						
						return "resultadoTipoHemograma";
					}
					 else
						 return "entradaDeResultadosTipoComum";
		 }
		
		return null;
		
		
	}
	
	public String botaoGravarResultados() throws SQLException, ClassNotFoundException
	{
		if(flagNovaEntrada)
			resultadoDAO.inserirNovoResultado(listaDeExibicao);
		else
			resultadoDAO.updateResultadoExistente(listaDeExibicao);
		
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
		return listaDeExibicao;
	}

	public void setListaSuporte(List<Resultado> listaSuporte) {
		this.listaDeExibicao = listaSuporte;
	}
}
