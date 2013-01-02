package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.MedicoDAO;
import labbios.dto.Medico;

public class CopyOfConversorMedico implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		MedicoDAO medicoDAO = new MedicoDAO();
		Medico medico = null;
		
		try
		{
			medico = medicoDAO.procurarMedicoPorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		return medico;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Medico medico = (Medico)arg2;
		return String.valueOf(medico.getMEDICO_ID());
	}

}
