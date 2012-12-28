package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.TipoLaboratorioDAO;
import labbios.dto.TipoLaboratorio;

public class ConversorTipoLaboratorio implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		TipoLaboratorioDAO tipoLaboratorioDAO = new TipoLaboratorioDAO();
		TipoLaboratorio tipoLaboratorio = null;
		
		try
		{
			tipoLaboratorio = tipoLaboratorioDAO.buscarTipoLaboratorioPorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		
		return tipoLaboratorio;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		TipoLaboratorio tipoLaboratorio = (TipoLaboratorio)arg2;
		return String.valueOf(tipoLaboratorio.getTIPO_LABORATORIO_ID());
	}

}
