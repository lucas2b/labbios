package labbios.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.CadastroDeExameDAO;
import labbios.dao.ConvenioDAO;
import labbios.dao.GrupoExameDAO;
import labbios.dao.MaterialExameDAO;
import labbios.dao.TabelaPrecosDAO;
import labbios.dao.TipoLaboratorioDAO;
import labbios.dto.CadastroDeExame;
import labbios.dto.Convenio;
import labbios.dto.GrupoExame;
import labbios.dto.MaterialExame;
import labbios.dto.TabelaPrecos;
import labbios.dto.TipoLaboratorio;

public class CadastroDeExameBean {
	CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
	ConvenioDAO convenioDAO = new ConvenioDAO();
	GrupoExameDAO grupoExameDAO = new GrupoExameDAO();
	MaterialExameDAO materialExameDAO = new MaterialExameDAO();
	TipoLaboratorioDAO tipoLaboratorioDAO = new TipoLaboratorioDAO();
	TabelaPrecosDAO tabelaDePrecosDAO = new TabelaPrecosDAO();
	
	private String abreviatura;
	private String nome;
	private String diasDeEntrega;
	private String codigoSUS;
	private int grupoEtiqueta;
	private char tipoDeEntrada;
	private double valorDoExame;
	
	private GrupoExame grupoExame;
	private TipoLaboratorio tipoLaboratorio;
	private MaterialExame materialExame;
	private CadastroDeExame cadastroDeExameSelecionado;
	private TabelaPrecos tabelaDePrecosSelecionada;
	private Convenio convenio;
	
	public double getValorDoExame() {
		return valorDoExame;
	}

	public void setValorDoExame(double valorDoExame) {
		this.valorDoExame = valorDoExame;
	}

	public TabelaPrecos getTabelaDePrecosSelecionado() {
		return tabelaDePrecosSelecionada;
	}

	public void setTabelaDePrecosSelecionado(TabelaPrecos tabelaDePrecosSelecionado) {
		this.tabelaDePrecosSelecionada = tabelaDePrecosSelecionado;
	}

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
		//O atributo tipoDeExameSelecionado j� recebeu uma inst�ncia de um tipo de exame presente na lista iterada na DataTable
		cadastroDeExameDAO.editarCadastroDeExame(cadastroDeExameSelecionado);
		return "listarTiposDeExames";
	}
	
	public List<Convenio> listarConvenios() throws ClassNotFoundException, SQLException
	{
		return convenioDAO.listarConvenios();
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
	
	public List<SelectItem> getConvenios() throws ClassNotFoundException, SQLException
	{
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Convenio convenio : convenioDAO.listarConvenios())
		{
			toReturn.add(new SelectItem(convenio, convenio.getCONVENIO_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;	
	}
	
	
	public TabelaPrecos getTabelaDePrecosSelecionada() {
		return tabelaDePrecosSelecionada;
	}

	public void setTabelaDePrecosSelecionada(TabelaPrecos tabelaDePrecosSelecionada) {
		this.tabelaDePrecosSelecionada = tabelaDePrecosSelecionada;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public CadastroDeExame getCadastroDeExameSelecionado() {
		return cadastroDeExameSelecionado;
	}

	public void setCadastroDeExameSelecionado(
			CadastroDeExame cadastroDeExameSelecionado) {
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
	
	public String adicionarValorDeExame() throws ClassNotFoundException, SQLException
	{
		TabelaPrecos tabelaPrecos = new TabelaPrecos();	
		tabelaPrecos.setTAB_PRECOS_VALOR(valorDoExame);
		tabelaPrecos.setCONVENIO(convenio);
		tabelaPrecos.setEXAME(cadastroDeExameSelecionado);
		
		tabelaDePrecosDAO.adicionarValor(tabelaPrecos);
		
		return "refresh";
		
	}
	
	public String startEditarValorDeExame()
	{
		return "editarValorDeExame";
	}
	
	
	public String finishEditarValorDeExame() throws ClassNotFoundException, SQLException
	{
		tabelaDePrecosDAO.editarValor(tabelaDePrecosSelecionada);
		return "listarValoresDeExames";
		
	}
	
	public List<TabelaPrecos> getValoresDeExameSelecionado() throws ClassNotFoundException, SQLException
	{
		return tabelaDePrecosDAO.listarTabelaDePrecosPorExame(cadastroDeExameSelecionado);
	}
	

}
