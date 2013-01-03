package labbios.beans;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.CadastroDeExameDAO;
import labbios.dao.ConvenioDAO;
import labbios.dao.ExameDAO;
import labbios.dao.MedicoDAO;
import labbios.dao.PacienteDAO;
import labbios.dao.SolicitacaoDAO;
import labbios.dao.StatusDAO;
import labbios.dao.TabelaPrecosDAO;
import labbios.dto.CadastroDeExame;
import labbios.dto.Convenio;
import labbios.dto.Exame;
import labbios.dto.Solicitacao;
import labbios.dto.TabelaPrecos;


public class SolicitacaoBean {
	
	//Declarações de SOLICITAÇÃO
	private Solicitacao solicitacaoSelecionada;
	
	//Declarações de EXAME
	private List<Exame> listaDeExames;
	
	//Declaração Compoenentes em Tela
	private String pacienteNome;
	private Convenio convenioEscolhido;
	private CadastroDeExame exameEscolhido;
	
	//DAOS
	private SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	private ExameDAO exameDAO = new ExameDAO();
	private PacienteDAO pacientesDAO = new PacienteDAO();
	private MedicoDAO medicosDAO = new MedicoDAO();
	private CadastroDeExameDAO cadastroDeExamesDAO = new CadastroDeExameDAO();
	private ConvenioDAO conveniosDAO = new ConvenioDAO();
	private TabelaPrecosDAO tabelaPrecosDAO = new TabelaPrecosDAO();
		
	public String adicionarSolicitacao() throws ClassNotFoundException, SQLException
	{
		solicitacaoDAO.adicionarSolicitacao(solicitacaoSelecionada);
		return "refresh";
	}
	
	public String adicionarExameASolicitacao() throws ClassNotFoundException, SQLException
	{
		Exame exameSelecionado = new Exame();
		
		exameSelecionado.setEXAME_DT_REALIZACAO(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		exameSelecionado.setEXAME_CATEGORIA_IP('A');
		
		StatusDAO status = new StatusDAO();
		exameSelecionado.setSTATUS(status.procurarStatusPorID(1));
		int ultimaSolicitacao = solicitacaoDAO.recuperarUltimoID();
		
		exameSelecionado.setCAD_EXAME(exameEscolhido);
		
		Solicitacao solicitacao = solicitacaoDAO.procurarSolicitacaoPorID(ultimaSolicitacao);
		
		exameSelecionado.setSOLICITACAO(solicitacao);
		
		TabelaPrecos tabelaPrecos = tabelaPrecosDAO.procurarIDPorConvenioEExame(convenioEscolhido, exameEscolhido);
		
		exameSelecionado.setEXAME_VALOR(tabelaPrecos);
		
		exameDAO.adicionarExame(exameSelecionado);
		
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
	
	public String retornarListagemSolicitacoes()
	{
		solicitacaoSelecionada = null;
		return "retornarListagemSolicitacoes";
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
	
	
	public List<SelectItem> getComboPacientes() throws ClassNotFoundException, SQLException
	{
		return pacientesDAO.getComboPacientes();
	}
	
	public List<SelectItem> getComboMedicos() throws ClassNotFoundException, SQLException
	{
		return medicosDAO.getComboMedicos();
	}
	
	public List<SelectItem> getComboExames() throws ClassNotFoundException, SQLException
	{
		return  cadastroDeExamesDAO.getComboExames();
	}
	
	public List<SelectItem> getComboConvenios() throws ClassNotFoundException, SQLException
	{
		return  conveniosDAO.getComboConvenios();
	}
	
	//GETTERS AND SETTERS
	
	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
	}


	public String getPacienteNome() {
		return pacienteNome;
	}

	public void setPacienteNome(String pacienteNome) {
		this.pacienteNome = pacienteNome;
	}

	public Convenio getConvenioEscolhido() {
		return convenioEscolhido;
	}

	public void setConvenioEscolhido(Convenio convenioEscolhido) {
		this.convenioEscolhido = convenioEscolhido;
	}

	public CadastroDeExame getExameEscolhido() {
		return exameEscolhido;
	}

	public void setExameEscolhido(CadastroDeExame exameEscolhido) {
		this.exameEscolhido = exameEscolhido;
	}
	

}
