package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.MaterialExameDAO;
import labbios.dto.MaterialExame;

public class ConversorMaterialExame implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {

		MaterialExameDAO materialExameDAO = new MaterialExameDAO();
		MaterialExame materialExame = null;
		
		try
		{
			materialExame = materialExameDAO.buscarMaterialExamePorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		
		return materialExame;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		MaterialExame materialExame = (MaterialExame) arg2;
		return materialExame.getMATERIAL_EXAME_NOME();
	}

}
