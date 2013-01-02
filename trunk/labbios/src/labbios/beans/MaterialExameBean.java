package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.MaterialExameDAO;
import labbios.dto.MaterialExame;

public class MaterialExameBean {
	MaterialExameDAO materialExameDAO = new MaterialExameDAO();
	MaterialExame materialExameSelecionado;
	private String nome;
	private String abreviatura;
	
	
	public String adicionarMaterialExame() throws ClassNotFoundException, SQLException
	{
		MaterialExame materialExame = new MaterialExame();
		materialExame.setMATERIAL_EXAME_ABREV(abreviatura);
		materialExame.setMATERIAL_EXAME_NOME(nome);
		materialExameDAO.adicionarMaterialExame(materialExame);
		return "refresh";
	}
	
	public String startEditarMaterialExame()
	{
		return "editarMaterialDeExame";
	}
	
	public String finishEditarMaterialExame() throws ClassNotFoundException, SQLException
	{
		materialExameDAO.editarMaterialExame(materialExameSelecionado);
		return "listarMateriaisDeExame";
	}
	
	public List<MaterialExame> getMateriaisExame() throws ClassNotFoundException, SQLException
	{
		return materialExameDAO.listarMaterialExame();
	}

	public MaterialExame getMaterialExameSelecionado() {
		return materialExameSelecionado;
	}

	public void setMaterialExameSelecionado(MaterialExame materialExameSelecionado) {
		this.materialExameSelecionado = materialExameSelecionado;
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

}
