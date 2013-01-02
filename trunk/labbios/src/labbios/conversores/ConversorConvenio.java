package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.ConvenioDAO;
import labbios.dto.Convenio;

public class ConversorConvenio implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		ConvenioDAO convenioDAO = new ConvenioDAO();
		Convenio convenio = null;	
		try
		{
			convenio = convenioDAO.buscarConvenioPorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		return convenio;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Convenio convenio = (Convenio)arg2;
		return String.valueOf(convenio.getCONVENIO_ID());
	}

}
