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
import labbios.dto.DadosDeExameGrade;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.GrupoExame;
import labbios.dto.MaterialExame;
import labbios.dto.TipoLaboratorio;

public class CadastroDeExameBean {
	
	//DAOS-----------------------------------------------------------
	
	CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
	TipoLaboratorioDAO tipoLaboratorioDAO = new TipoLaboratorioDAO();
	MaterialExameDAO   materialExameDAO   = new MaterialExameDAO();
	DadosDoExameDAO    dadosDoExameDAO    = new DadosDoExameDAO();
	GrupoExameDAO      grupoExameDAO      = new GrupoExameDAO();
	ConvenioDAO        convenioDAO        = new ConvenioDAO();
	
	//----------------------------------------------------------------
	
	
	//Componentes de Tela---------------------------------------------
	
	private String dadosDeExameEmTexto;
	private boolean flagGravar; 
	private List<DadosDeExameGrade> dadosDeExameEmGrade;
	private CadastroDeExame cadastroDeExameSelecionado = new CadastroDeExame();
	
	public CadastroDeExameBean() {
		new CadastroDeExame();
	}

	public String adicionarNovoTipoDeExame() throws ClassNotFoundException, SQLException
	{	
		cadastroDeExameDAO.adicionarNovoExame(cadastroDeExameSelecionado);
		cadastroDeExameSelecionado = new CadastroDeExame();
		return "refresh";
	}
	
	public String startEditarTipoDeExame()
	{
		return "editarExameCadastrado";
	}
	
	public String finishEditarTipoDeExame() throws ClassNotFoundException, SQLException
	{
		//O atributo tipoDeExameSelecionado j� recebeu uma inst�ncia de um tipo de exame presente na lista iterada na DataTable
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
	
	public String entrarDadosDoExame() throws ClassNotFoundException, SQLException
	{
		boolean tabelaExistente = dadosDoExameDAO.verificarExistenciaDeTabela(cadastroDeExameSelecionado);
		
		if(tabelaExistente)
		{	
			flagGravar = false;
			//Se existe a listagem deste exame
			
			if(cadastroDeExameSelecionado.getCAD_EXAME_TIPO_ENTRADA() == 'G')
			{
				
				dadosDeExameEmGrade = dadosDoExameDAO.recuperarTabela(cadastroDeExameSelecionado);
				return "entrarDadosDoExame";
			}
			else
			{
				dadosDeExameEmTexto= dadosDoExameDAO.recuperarTexto(cadastroDeExameSelecionado);
				return "entrarDadosDoExameTexto";
				
			}
		}
		else
		{
			//Caso n�o exista, apresenta 35 linhas em branco
			flagGravar = true;
			
			if(cadastroDeExameSelecionado.getCAD_EXAME_TIPO_ENTRADA() == 'G')
			{
				dadosDeExameEmGrade = new LinkedList<DadosDeExameGrade>();
				for(int i=0; i<35; i++)
				{
					dadosDeExameEmGrade.add(new DadosDeExameGrade());			
				}
				return "entrarDadosDoExame";
			}
			else
			{
				dadosDeExameEmTexto = new String();
				return "entrarDadosDoExameTexto";
			}
		}

	}
	
	public String gravarDadosDoExame() throws ClassNotFoundException, SQLException
	{
		if(flagGravar== true)
		{
			//nova tabela
			if(cadastroDeExameSelecionado.getCAD_EXAME_TIPO_ENTRADA() == 'G')
			dadosDoExameDAO.adicionarDadosDoExameEmGrade(dadosDeExameEmGrade, cadastroDeExameSelecionado);
			else
			dadosDoExameDAO.adicionarDadosDoExameEmTexto(dadosDeExameEmTexto, cadastroDeExameSelecionado);
			
		}
		else
		{
			if(cadastroDeExameSelecionado.getCAD_EXAME_TIPO_ENTRADA() == 'G')
			dadosDoExameDAO.atualizarDadosDoExameEmGrade(dadosDeExameEmGrade, cadastroDeExameSelecionado);
			else
			dadosDoExameDAO.editarDadosDoExameEmTexto(dadosDeExameEmTexto, cadastroDeExameSelecionado);
		}
		
		return listarExames();
	}
	
	public String listarExames()
	{
		return "listarExames";
	}
	
	public List<SelectItem> getComboGradeOuExame()
	{
		List<SelectItem> listaGradeOuExame = new LinkedList<SelectItem>();
		listaGradeOuExame.add(new SelectItem("G","G"));
		listaGradeOuExame.add(new SelectItem("T","T"));
		return listaGradeOuExame;
	}


	//GETTERS AND SETTERS
	

	public CadastroDeExame getCadastroDeExameSelecionado() {
		return cadastroDeExameSelecionado;
	}

	public void setCadastroDeExameSelecionado(CadastroDeExame cadastroDeExameSelecionado) {
		this.cadastroDeExameSelecionado = cadastroDeExameSelecionado;
	}

	
	
	public String associarValorAoExame()
	{
		return "associarValorAoExame";
	}
	

	public String getDadosDeExameEmTexto() {
		return dadosDeExameEmTexto;
	}

	public void setDadosDeExameEmTexto(String dadosDeExameEmTexto) {
		this.dadosDeExameEmTexto = dadosDeExameEmTexto;
	}

	public List<DadosDeExameGrade> getDadosDeExameEmGrade() {
		return dadosDeExameEmGrade;
	}

	public void setDadosDeExameEmGrade(List<DadosDeExameGrade> dadosDeExameEmGrade) {
		this.dadosDeExameEmGrade = dadosDeExameEmGrade;
	}

}
