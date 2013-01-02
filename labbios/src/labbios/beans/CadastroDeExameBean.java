package labbios.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import labbios.dao.CadastroDeExameDAO;
import labbios.dao.ConvenioDAO;
import labbios.dao.DadosDoExameDAO;
import labbios.dao.GrupoExameDAO;
import labbios.dao.MaterialExameDAO;
import labbios.dao.TipoLaboratorioDAO;
import labbios.dto.CadastroDeExame;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.GrupoExame;
import labbios.dto.MaterialExame;
import labbios.dto.TipoLaboratorio;

public class CadastroDeExameBean {
	CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
	ConvenioDAO convenioDAO = new ConvenioDAO();
	GrupoExameDAO grupoExameDAO = new GrupoExameDAO();
	MaterialExameDAO materialExameDAO = new MaterialExameDAO();
	TipoLaboratorioDAO tipoLaboratorioDAO = new TipoLaboratorioDAO();
	DadosDoExameDAO dadosDoExameDAO = new DadosDoExameDAO();
	
	private String abreviatura;
	private String nome;
	private String diasDeEntrega;
	private String codigoSUS;
	private int grupoEtiqueta;
	private char tipoDeEntrada;
	private boolean flagGravar = false; 
	
	private List<DadosDoExameSuporte> listaSuporte;
	private GrupoExame grupoExame;
	private TipoLaboratorio tipoLaboratorio;
	private MaterialExame materialExame;
	private CadastroDeExame cadastroDeExameSelecionado;
	private String nomeDoExameSelecionado;

	public String adicionarNovoTipoDeExame() throws ClassNotFoundException, SQLException
	{	
		
		CadastroDeExame cadastroDeExame = new CadastroDeExame();
		cadastroDeExame.setCAD_EXAME_ABREVIATURA(abreviatura);
		cadastroDeExame.setCAD_EXAME_NOME(nome);
		cadastroDeExame.setCAD_EXAME_DIAS_ENTREGA(diasDeEntrega);
		cadastroDeExame.setCAD_EXAME_COD_SUS(codigoSUS);
		cadastroDeExame.setCAD_EXAME_GRUPO_ETIQUETA(grupoEtiqueta);
		cadastroDeExame.setCAD_EXAME_TIPO_ENTRADA(tipoDeEntrada);
		cadastroDeExame.setGRUPO_EXAME(grupoExame);
		cadastroDeExame.setTIPO_LABORATORIO(tipoLaboratorio);
		cadastroDeExame.setMATERIAL_EXAME(materialExame);
		
		cadastroDeExameDAO.adicionarNovoExame(cadastroDeExame);	

		return "refresh";
	}
	
	public String startEditarTipoDeExame()
	{
		return "editarTipoDeExame";
	}
	
	public String finishEditarTipoDeExame() throws ClassNotFoundException, SQLException
	{
		//O atributo tipoDeExameSelecionado já recebeu uma instância de um tipo de exame presente na lista iterada na DataTable
		cadastroDeExameDAO.editarCadastroDeExame(cadastroDeExameSelecionado);
		return "listarTiposDeExames";
	}
	
	public List<CadastroDeExame> getTiposDeExames() throws ClassNotFoundException, SQLException
	{
		return cadastroDeExameDAO.listarCadastroDeExames();
	}

	public List<SelectItem> getGrupos() throws ClassNotFoundException, SQLException
	{
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(GrupoExame grupo : grupoExameDAO.listarGrupoExames())
		{
			toReturn.add(new SelectItem(grupo, grupo.getGRUPO_EXAME_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;	
		
	}
	
	public List<SelectItem> getMateriais() throws ClassNotFoundException, SQLException
	{
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(MaterialExame material : materialExameDAO.listarMaterialExame())
		{
			toReturn.add(new SelectItem(material, material.getMATERIAL_EXAME_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;	
	}
	
	public List<SelectItem> getTiposDeLaboratorio() throws ClassNotFoundException, SQLException
	{
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(TipoLaboratorio tipoLab : tipoLaboratorioDAO.listarTipoLaboratorio())
		{
			toReturn.add(new SelectItem(tipoLab, tipoLab.getTIPO_LABORATORIO_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;	
	}
	
	public String setarDadosSessao()
	{
	
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("exameNome", cadastroDeExameSelecionado.getCAD_EXAME_NOME());
		return "entrarDadosDoExame"; 
	}
	
	public String entrarDadosDoExame()
	{
		try{
		boolean flagRetorno = dadosDoExameDAO.verificarExistenciaDeTabela(nomeDoExameSelecionado);
		
		if(flagRetorno)
		{	
			//Se existe a listagem deste exame
			System.out.println("Existe Listagem");
			listaSuporte = dadosDoExameDAO.recuperarTabela(nomeDoExameSelecionado);
		}
		else
		{
			//Caso não exista, apresenta 35 linhas em branco
			System.out.println("Nao Existe Listagem");
			flagGravar = true;
			listaSuporte = new LinkedList<DadosDoExameSuporte>();
			
			for(int i=0; i<35; i++)
			{
				listaSuporte.add(new DadosDoExameSuporte());			
			}
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return "entrarDadosDoExame";

	}
	
	public List<DadosDoExameSuporte> getListaSuporte() {
		return listaSuporte;
	}
	
	public void setListaSuporte(List<DadosDoExameSuporte> listaSuporte) {
		this.listaSuporte = listaSuporte;
	}
	
	public String gravarDadosDoExame() throws ClassNotFoundException, SQLException
	{
		if(flagGravar== true)
		{
			//nova tabela
			dadosDoExameDAO.adicionarDadosDoExame(listaSuporte, nomeDoExameSelecionado);
			
		}
		else
		{
			dadosDoExameDAO.atualizarDadosDoExame(listaSuporte, nomeDoExameSelecionado);
			//update
		}
		
		return listarExames();
	}
	
	public String listarExames()
	{
		return "listarExames";
	}


	//GETTERS AND SETTERS
	

	public CadastroDeExame getCadastroDeExameSelecionado() {
		return cadastroDeExameSelecionado;
	}

	public void setCadastroDeExameSelecionado(CadastroDeExame cadastroDeExameSelecionado) {
		this.cadastroDeExameSelecionado = cadastroDeExameSelecionado;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public GrupoExame getGrupoExame() {
		return grupoExame;
	}

	public void setGrupoExame(GrupoExame grupoExame) {
		this.grupoExame = grupoExame;
	}

	public TipoLaboratorio getTipoLaboratorio() {
		return tipoLaboratorio;
	}

	public void setTipoLaboratorio(TipoLaboratorio tipoLaboratorio) {
		this.tipoLaboratorio = tipoLaboratorio;
	}

	public MaterialExame getMaterialExame() {
		return materialExame;
	}

	public void setMaterialExame(MaterialExame materialExame) {
		this.materialExame = materialExame;
	}

	public String getDiasDeEntrega() {
		return diasDeEntrega;
	}

	public void setDiasDeEntrega(String diasDeEntrega) {
		this.diasDeEntrega = diasDeEntrega;
	}

	public String getCodigoSUS() {
		return codigoSUS;
	}

	public void setCodigoSUS(String codigoSUS) {
		this.codigoSUS = codigoSUS;
	}

	public int getGrupoEtiqueta() {
		return grupoEtiqueta;
	}

	public void setGrupoEtiqueta(int grupoEtiqueta) {
		this.grupoEtiqueta = grupoEtiqueta;
	}

	public char getTipoDeEntrada() {
		return tipoDeEntrada;
	}

	public void setTipoDeEntrada(char tipoDeEntrada) {
		this.tipoDeEntrada = tipoDeEntrada;
	}
	
	public String associarValorAoExame()
	{
		return "associarValorAoExame";
	}

	public String getNomeDoExameSelecionado() {
		return nomeDoExameSelecionado;
	}

	public void setNomeDoExameSelecionado(String nomeDoExameSelecionado) {
		this.nomeDoExameSelecionado = nomeDoExameSelecionado;
	}
}
