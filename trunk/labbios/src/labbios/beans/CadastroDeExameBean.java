package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.CadastroDeExameDAO;
import labbios.dao.ConvenioDAO;
import labbios.dto.CadastroDeExame;
import labbios.dto.Convenio;
import labbios.dto.GrupoExame;
import labbios.dto.MaterialExame;
import labbios.dto.TipoLaboratorio;

public class CadastroDeExameBean {
	CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
	ConvenioDAO convenioDAO = new ConvenioDAO();
	
	CadastroDeExame cadastroDeExameSelecionado = new CadastroDeExame();
	
	private String abreviatura;
	private String nome;
	private GrupoExame grupoExame;
	private TipoLaboratorio tipoLaboratorio;
	private MaterialExame materialExame;
	private String diasDeEntrega;
	private String codigoSUS;
	private int grupoEtiqueta;
	private char tipoDeEntrada;
	
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
	
	public List<Convenio> listarConvenios() throws ClassNotFoundException, SQLException
	{
		return convenioDAO.listarConvenios();
	}
	
	public List<CadastroDeExame> getTiposDeExames() throws ClassNotFoundException, SQLException
	{
		return cadastroDeExameDAO.listarCadastroDeExames();
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
	
	

}
