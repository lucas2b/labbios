package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.CidadeDAO;
import labbios.dto.Cidade;

public class ConversorCidade implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		CidadeDAO cidadesDAO = new CidadeDAO();
		Cidade cidade = null;
		try
		{
			cidade = cidadesDAO.procurarCidadePorID(Integer.parseInt(arg2));
		}
		catch(Exception e){}
		return cidade;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Cidade cidade = (Cidade)arg2;
		return String.valueOf(cidade.getCIDADE_ID());
	}

}
