package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.GrupoExameDAO;
import labbios.dto.GrupoExame;

public class ConversorGrupoExame implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		GrupoExameDAO grupoExameDAO = new GrupoExameDAO();
		GrupoExame grupoExame = null;
		
		try
		{
			grupoExame = grupoExameDAO.buscarGrupoExamePorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		
		return grupoExame;
		
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {		
		GrupoExame grupoExame = (GrupoExame) arg2;
		return grupoExame.getGRUPO_EXAME_NOME();
	}

}
