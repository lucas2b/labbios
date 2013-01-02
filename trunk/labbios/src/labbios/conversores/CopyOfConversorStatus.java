package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.StatusDAO;
import labbios.dto.Status;

public class CopyOfConversorStatus implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("Chegou no Conversor Paciente status");
		StatusDAO statusDAO = new StatusDAO();
		Status status = null;
		
		try
		{
			status = statusDAO.procurarStatusPorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		return status;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Status status = (Status)arg2;
		return String.valueOf(status.getSTATUS_ID());
	}

}
