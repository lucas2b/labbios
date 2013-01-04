package labbios.beans;

import java.sql.SQLException;
import java.util.LinkedList;
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
	private Solicitacao solicitacaoSelecionada;
	
	private boolean flagNovaSolicitacao;
	private boolean flagSolicitacaoCriada;
	
	private List<Exame> listaDeExames;
	
	private Convenio convenioEscolhido;
	private CadastroDeExame exameEscolhido;
	
	private SolicitacaoDAO solicitacaoDAO = new SolicitacaoDAO();
	private ExameDAO examesDAO = new ExameDAO();
	private PacienteDAO pacientesDAO = new PacienteDAO();
	private MedicoDAO medicosDAO = new MedicoDAO();
	private CadastroDeExameDAO cadastroDeExamesDAO = new CadastroDeExameDAO();
	private ConvenioDAO conveniosDAO = new ConvenioDAO();
	private TabelaPrecosDAO tabelaPrecosDAO = new TabelaPrecosDAO();
	private StatusDAO statusDAO = new StatusDAO();
		
	
	public String manutencaoDeSolicitacao() throws ClassNotFoundException, SQLException
	{
		if(solicitacaoSelecionada != null)
		{
			//Rotina de solicitação existente
			flagNovaSolicitacao = false;
			solicitacaoSelecionada = solicitacaoDAO.recuperarSolicitacao(solicitacaoSelecionada);
			listaDeExames = examesDAO.recuperarExamesPorSolicitacao(solicitacaoSelecionada);
		}
		else
		{
			flagNovaSolicitacao = true;
			flagSolicitacaoCriada = true;
			solicitacaoSelecionada = new Solicitacao();
			listaDeExames = new LinkedList<Exame>();
		}
		return "editarSolicitacao";
	}
	
	public String adicionarExameASolicitacao() throws ClassNotFoundException, SQLException
	{
		if(flagSolicitacaoCriada)
		{
			solicitacaoDAO.adicionarSolicitacao(solicitacaoSelecionada);
			//Adicionando uma SOLICITAÇÃO para que POSSAM ser incluídos os EXAMES, por uma questão de ordem
			flagSolicitacaoCriada = false;
		}
		
		Exame inserirExame = new Exame();
		
		TabelaPrecos tabelaPrecos = tabelaPrecosDAO.procurarIDPorConvenioEExame(convenioEscolhido, exameEscolhido);
		//Procura o ID correspondente ao convenio e ao exame escolhidos na Tabela De Precos
		
		inserirExame.setEXAME_VALOR(tabelaPrecos);
		inserirExame.setEXAME_CATEGORIA_IP('A');
		inserirExame.setSTATUS(statusDAO.procurarStatusPorID(2));
		inserirExame.setCAD_EXAME(exameEscolhido);
		inserirExame.setSOLICITACAO(solicitacaoDAO.procurarSolicitacaoPorID(solicitacaoDAO.recuperarUltimoID()));
		
		listaDeExames.add(inserirExame);	
		return "refresh";
	}
	
	public String editarListaDeExames()
	{
		return "refresh";
	}
	
	
	public String botaoGravar() throws ClassNotFoundException, SQLException
	{
		if(flagNovaSolicitacao)
		{
			//Rotina de inserção de exames na solicitação
			for(Exame exame : listaDeExames)
				examesDAO.adicionarExame(exame);
			
			return retornarListagemSolicitacoes();
		}
		else
		{
			//Rotina de edição de solicitação
			solicitacaoDAO.editarSolicitacao(solicitacaoSelecionada);
			
			for(Exame exame : listaDeExames)
				examesDAO.editarExame(exame);
			
			return manutencaoDeSolicitacao();
		}
	}
	
	public String retornarListagemSolicitacoes()
	{
		solicitacaoSelecionada = null;
		listaDeExames.clear();
		return "retornarListagemSolicitacoes";
	}
	
	public List<Solicitacao> getSolicitacoes() throws ClassNotFoundException, SQLException
	{
		return solicitacaoDAO.listarSolicitacoes();
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
}
