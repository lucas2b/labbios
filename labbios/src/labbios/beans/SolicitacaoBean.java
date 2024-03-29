package labbios.beans;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.AnimalDAO;
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

	//DAOS
	
	private CadastroDeExameDAO cadastroDeExamesDAO = new CadastroDeExameDAO();
	private SolicitacaoDAO     solicitacaoDAO      = new SolicitacaoDAO();
	private TabelaPrecosDAO    tabelaPrecosDAO     = new TabelaPrecosDAO();
	private ConvenioDAO        conveniosDAO        = new ConvenioDAO();
	private PacienteDAO        pacientesDAO        = new PacienteDAO();
	private MedicoDAO          medicosDAO          = new MedicoDAO();
	private StatusDAO          statusDAO           = new StatusDAO();
	private ExameDAO           examesDAO           = new ExameDAO();
	private AnimalDAO		   animalDAO		   = new AnimalDAO();
	private Solicitacao 	   solicitacaoSelecionada;
	
	//------------------------------------------------------------------------
	
	//Componentes de TELA
	
	private Convenio 		 convenioEscolhido;
	private CadastroDeExame  exameEscolhido;
	private boolean			 flagUrgente;
	
	//------------------------------------------------------------------------
	
	private boolean flagNovaSolicitacao;
	private List<Exame> listaDeExames;
	
	
		
	
	public String manutencaoDeSolicitacao() throws ClassNotFoundException, SQLException
	{
		if(solicitacaoSelecionada != null)
		{
			if(solicitacaoSelecionada.getSOL_ID() == 0)
			{
				flagNovaSolicitacao = true;
				solicitacaoSelecionada = new Solicitacao();
				listaDeExames = new LinkedList<Exame>();
			}
			else
			{
				
			//Rotina de solicita��o existente
			flagNovaSolicitacao = false;
			solicitacaoSelecionada = solicitacaoDAO.recuperarSolicitacao(solicitacaoSelecionada);
			
			if( solicitacaoSelecionada.getSOL_FLAG_URGENTE() == 1)
				flagUrgente= true;
			else
				flagUrgente = false;
			
			listaDeExames = examesDAO.recuperarExamesPorSolicitacao(solicitacaoSelecionada);
			
			}
		}
		else
		{
			//Rotina de nova solicita��o
			flagNovaSolicitacao = true;
			solicitacaoSelecionada = new Solicitacao();
			listaDeExames = new LinkedList<Exame>();
		}
		return "editarSolicitacao";
	}
	
	public String adicionarExameASolicitacao() throws ClassNotFoundException, SQLException
	{	
		Exame inserirExame = new Exame();
		
		TabelaPrecos tabelaPrecos = tabelaPrecosDAO.procurarIDPorConvenioEExame(convenioEscolhido, exameEscolhido);
		//Procura o ID correspondente ao convenio e ao exame escolhidos na Tabela De Precos
		
		inserirExame.setEXAME_VALOR(tabelaPrecos);
		inserirExame.setEXAME_CATEGORIA_IP('A');
		inserirExame.setSTATUS(statusDAO.procurarStatusPorID(1));
		inserirExame.setEXAME_DT_REALIZACAO(new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		inserirExame.setCAD_EXAME(exameEscolhido);
		
		listaDeExames.add(inserirExame);	
		return "refresh";
	}
	
	
	public String botaoGravar() throws ClassNotFoundException, SQLException
	{
		if(flagNovaSolicitacao)
		{
			//Rotina de inser��o de exames na solicita��o
			
			if(flagUrgente == true)
				solicitacaoSelecionada.setSOL_FLAG_URGENTE(1);
			else
				solicitacaoSelecionada.setSOL_FLAG_URGENTE(0);
			
			solicitacaoSelecionada.setSTATUS(statusDAO.procurarStatusPorID(1));
			solicitacaoDAO.adicionarSolicitacao(solicitacaoSelecionada);
			
			for(Exame exame : listaDeExames)
			{
				exame.setSOLICITACAO(solicitacaoDAO.procurarSolicitacaoPorID(solicitacaoDAO.recuperarUltimoID()));
				examesDAO.adicionarExame(exame);
			}
			
			return retornarListagemSolicitacoes();
		}
		else
		{
			//Rotina de edi��o de solicita��o
			
			if(flagUrgente == true)
				solicitacaoSelecionada.setSOL_FLAG_URGENTE(1);
			else
				solicitacaoSelecionada.setSOL_FLAG_URGENTE(0);
			
			solicitacaoDAO.atualizarSolicitacao(solicitacaoSelecionada);
			
			for(Exame exame : listaDeExames)
			{
				
				if(exame.getEXAME_ID() == 0)
				{
					exame.setSOLICITACAO(solicitacaoDAO.procurarSolicitacaoPorID(solicitacaoSelecionada.getSOL_ID()));
					examesDAO.adicionarExame(exame);
				}
				else
				{
					
					exame.setSOLICITACAO(solicitacaoDAO.procurarSolicitacaoPorID(solicitacaoSelecionada.getSOL_ID()));
					examesDAO.atualizarExame(exame);
				}
				
			}
			
			return retornarListagemSolicitacoes();
		}
	}
	
	public String retornarListagemSolicitacoes()
	{
		solicitacaoSelecionada = null;
		listaDeExames.clear();
		return "retornarListagemSolicitacoes";
	}
	
	public String limparTela() throws ClassNotFoundException, SQLException
	{
		solicitacaoSelecionada = null;
		return manutencaoDeSolicitacao();
	}
	
	
	public List<SelectItem> getComboUrgente()
	{
		List<SelectItem> flagUrgente = new LinkedList<SelectItem>();
		flagUrgente.add(new SelectItem(true, "Sim"));
		flagUrgente.add(new SelectItem(false, "N�o"));
		
		return flagUrgente;
	}
	
	public List<Solicitacao> getSolicitacoes() throws ClassNotFoundException, SQLException
	{
		return solicitacaoDAO.listarSolicitacoes();
	}
		
	public List<SelectItem> getComboPacientes() throws ClassNotFoundException, SQLException
	{
		return pacientesDAO.getComboPacientes();
	}
	
	public List<SelectItem> getComboStatus() throws ClassNotFoundException, SQLException
	{
		return statusDAO.getComboStatus();
	}
	
	public List<SelectItem> getComboMedicos() throws ClassNotFoundException, SQLException
	{
		return medicosDAO.getComboMedicos();
	}
	
	public List<SelectItem> getComboExames() throws ClassNotFoundException, SQLException
	{
		return  cadastroDeExamesDAO.getComboExames();
	}
	
	public List<SelectItem> getComboAnimais() throws ClassNotFoundException, SQLException
	{
		return  animalDAO.getComboAnimais();
	}
	
	public List<SelectItem> getComboConvenios() throws ClassNotFoundException, SQLException
	{
		if(exameEscolhido != null)
			return  tabelaPrecosDAO.associaConvenioaoExame(exameEscolhido);
		else
			return conveniosDAO.getComboConvenios();	
	}
	
	public List<Exame> recuperarExamesPendentes() throws SQLException, ClassNotFoundException
	{
		return examesDAO.recuperarExamesPendentes();
	}
	
	public List<Exame> recuperarExamesEmAndamento() throws SQLException, ClassNotFoundException
	{
		return examesDAO.recuperarExamesEmAndamento();
	}
	
	public List<Exame> recuperarExamesFinalizados() throws SQLException, ClassNotFoundException
	{
		return examesDAO.recuperarExamesFinalizados();
	}
	
	public List<Solicitacao> recuperarSolicitacoesCadastradas() throws SQLException, ClassNotFoundException
	{
		return solicitacaoDAO.recuperarSolicitacoesCadastradas();
	}
	
	public List<Solicitacao> recuperarSolicitacoesEmAndamento() throws SQLException, ClassNotFoundException
	{
		return solicitacaoDAO.recuperarSolicitacoesEmAndamento();
	}
	
	public List<Solicitacao> recuperarSolicitacoesFinalizadas() throws SQLException, ClassNotFoundException
	{
		return solicitacaoDAO.recuperarSolicitacoesFinalizadas();
	}
	
	public List<Solicitacao> recuperarSolicitacoesEntregues() throws SQLException, ClassNotFoundException
	{
		return solicitacaoDAO.recuperarSolicitacoesEntregues();
	}
	
	
	
	
	//GETTERS AND SETTERS
	
	public Solicitacao getSolicitacaoSelecionada() {
		return solicitacaoSelecionada;
	}

	public void setSolicitacaoSelecionada(Solicitacao solicitacaoSelecionada) {
		this.solicitacaoSelecionada = solicitacaoSelecionada;
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

	public List<Exame> getListaDeExames() {
		return listaDeExames;
	}

	public void setListaDeExames(List<Exame> listaDeExames) {
		this.listaDeExames = listaDeExames;
	}

	public boolean isFlagUrgente() {
		return flagUrgente;
	}

	public void setFlagUrgente(boolean flagUrgente) {
		this.flagUrgente = flagUrgente;
	}
}
