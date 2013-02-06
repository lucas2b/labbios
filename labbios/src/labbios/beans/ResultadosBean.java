package labbios.beans;


import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import labbios.dao.DadosDoExameDAO;
import labbios.dao.ExameDAO;
import labbios.dao.ResultadoDAO;
import labbios.dto.DadosDeExameGrade;
import labbios.dto.Exame;
import labbios.dto.Resultado;

public class ResultadosBean {
	
	//Atributos de controle
	private Exame exameSelecionado;
	private boolean flagNovaEntrada;
	
	//DAOS usados
	private DadosDoExameDAO dadosDoExameDAO = new DadosDoExameDAO();
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	private ExameDAO exameDAO = new ExameDAO();
	
	
	//Listas de auxílio
	private List<Resultado> listaDeExibicao = new LinkedList<Resultado>();
	private List<DadosDeExameGrade> dadosDeExameGrade; //Molde da tabela de exames recuperado
	
	//Função de início
	public String inicioEntradaResultado() throws SQLException, ClassNotFoundException
	{
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"A solicitação ainda não foi salva! Clique em salvar e retorne para entrar resultados!", ""));
			return null;
		}
			
		if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
		{
				flagNovaEntrada = false;
				listaDeExibicao = resultadoDAO.recuperarResultado(exameSelecionado);

		}
		else
		{
				flagNovaEntrada = true;
				dadosDeExameGrade = dadosDoExameDAO.recuperarTabela(exameSelecionado.getCAD_EXAME());
				listaDeExibicao.clear();
				for(DadosDeExameGrade molde: dadosDeExameGrade)
				{
					Resultado resultado = new Resultado();
					resultado.setEXAME(exameDAO.buscarExamePorID(exameSelecionado.getEXAME_ID()));
					resultado.setRESULT_PARAMETRO(molde.getDADOS_GRADE_PARAMETRO());
					resultado.setRESULT_UNIDADE(molde.getDADOS_GRADE_UNIDADE());
					resultado.setRESULT_VALOR_REFERENCIA(molde.getDADOS_GRADE_REFERENCIA());
					resultado.setRESULT_OBSERVACOES("");
					listaDeExibicao.add(resultado);
				}
				
				
		}					
			return "resultadoGrade";
		
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
		listaDeExibicao.clear();
		return "editarSolicitacao";
	}
	
	
	
	//GETTERS AND SETTERS

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}


	public List<Resultado> getListaDeExibicao() {
		return listaDeExibicao;
	}

	public void setListaDeExibicao(List<Resultado> listaDeExibicao) {
		this.listaDeExibicao = listaDeExibicao;
	}
}