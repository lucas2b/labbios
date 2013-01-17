package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.MaterialExameDAO;
import labbios.dto.MaterialExame;

public class MaterialExameBean {
	MaterialExameDAO materialExameDAO = new MaterialExameDAO();
	MaterialExame materialExameSelecionado = new MaterialExame();
	

	
	
	public String adicionarMaterialExame() throws ClassNotFoundException, SQLException
	{
		materialExameDAO.adicionarMaterialExame(materialExameSelecionado);
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

}
