package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.GrupoExameDAO;
import labbios.dto.GrupoExame;

public class GrupoExameBean {
	
	GrupoExameDAO grupoExameDAO = new GrupoExameDAO();
	
	private String nome;
	private String abreviatura;
	private GrupoExame grupoExameSelecionado;
	
	public String addGrupoExame() throws ClassNotFoundException, SQLException
	{
		GrupoExame grupoExame = new GrupoExame();
		grupoExame.setGRUPO_EXAME_ABREV(abreviatura);
		grupoExame.setGRUPO_EXAME_NOME(nome);
		grupoExameDAO.adicionarGrupoExame(grupoExame);
		return "refresh";
	}
	
	public List<GrupoExame> getGruposDeExames() throws ClassNotFoundException, SQLException
	{
		return grupoExameDAO.listarGrupoExames();
	}
	
	public String startEditarGrupoExame()
	{
		return "editarGrupoDeExames";
	}
	
	public String finishEditarGrupoExame() throws ClassNotFoundException, SQLException
	{
		grupoExameDAO.editarGrupoExame(grupoExameSelecionado);
		return "grupoDeExames";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAbreviatura() {
		return abreviatura;
	}

	public void setAbreviatura(String abreviatura) {
		this.abreviatura = abreviatura;
	}

	public GrupoExame getGrupoExameSelecionado() {
		return grupoExameSelecionado;
	}

	public void setGrupoExameSelecionado(GrupoExame grupoExameSelecionado) {
		this.grupoExameSelecionado = grupoExameSelecionado;
	}

}
