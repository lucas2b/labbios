package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.CadastroDeExameDAO;
import labbios.dao.ExameDAO;
import labbios.dao.PacienteDAO;
import labbios.dao.SolicitacaoDAO;
import labbios.dao.StatusDAO;
import labbios.dto.Exame;
import labbios.dto.Solicitacao;


public class SolicitacaoBean {
	
	//Declarações de SOLICITAÇÃO
	private Solicitacao solicitacaoSelecionada;
	
	//Declarações de EXAME
	private Exame exameSelecionado;
	private List<Exame> listaDeExames;
	
	//DAOS
	private SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	private StatusDAO statusDAO = new StatusDAO();
	private CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
	private ExameDAO exameDAO = new ExameDAO();
	private PacienteDAO pacientesDAO = new PacienteDAO();
		
	public String adicionarSolicitacao() throws ClassNotFoundException, SQLException
	{	
		solicitacaoDAO.adicionarSolicitacao(solicitacaoSelecionada);
		return "refresh";
	}
	
	public String atualizarSolicitacao() throws ClassNotFoundException, SQLException
	{
		solicitacaoDAO.editarSolicitacao(solicitacaoSelecionada);	
		return "refresh";
	}
	
	public String manutencaoDeSolicitacao() throws ClassNotFoundException, SQLException
	{
		if(solicitacaoSelecionada != null)
		{
			//Rotina de solicitação existente
			solicitacaoSelecionada = solicitacaoDAO.recuperarSolicitacao(solicitacaoSelecionada);
			//exameSelecionado = exameDAO.recuperarExameViaSolicitacaoID(solicitacaoSelecionada);
		}
		else
		{
			solicitacaoSelecionada = new Solicitacao();
		}
		return "editarSolicitacao";
	}
	
	public List<Solicitacao> getSolicitacoes() throws ClassNotFoundException, SQLException
	{
		return solicitacaoDAO.listarSolicitacoes();
	}
	
	public void adicionarListaDeExames() throws ClassNotFoundException, SQLException
	{
		for(Exame exame : listaDeExames)
		{
			exameDAO.adicionarExame(exame);
		}
	}
	
	public String atualizarListaDeExames()
	{
		listaDeExames.add(exameSelecionado);
		return "refresh";
	}
	
	public List<SelectItem> getComboPacientes() throws ClassNotFoundException, SQLException
	{
		return pacientesDAO.getComboPacientes();
	}
	

	
	//GETTERS AND SETTERS
	
	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}
	

}
