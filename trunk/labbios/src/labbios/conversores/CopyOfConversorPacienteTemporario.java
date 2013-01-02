package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import labbios.dao.PacienteTemporarioDAO;

import labbios.dto.PacienteTemporario;

public class CopyOfConversorPacienteTemporario implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		System.out.println("Chegou no Conversor Paciente temp");
		PacienteTemporarioDAO temporarioDAO = new PacienteTemporarioDAO();
		PacienteTemporario temporario = null;
		
		try
		{
			temporario = temporarioDAO.procurarPacienteTemporarioPorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		return temporario;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		PacienteTemporario temporario = (PacienteTemporario)arg2;
		return String.valueOf(temporario.getPACIENTE_TEMPORARIO_ID());
	}

}
