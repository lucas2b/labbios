package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.AnimalDAO;
import labbios.dao.PacienteDAO;
import labbios.dto.Animal;
import labbios.dto.Paciente;

public class ConversorPaciente implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		PacienteDAO pacienteDAO = new PacienteDAO();
		Paciente paciente = null;	
		
		try
		{
			paciente = pacienteDAO.procurarPacientePorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		return paciente;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Paciente paciente = (Paciente)arg2;
		return String.valueOf(paciente.getPACIENTE_ID());
	}

}
