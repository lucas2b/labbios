package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.CadastroDeExameDAO;
import labbios.dto.CadastroDeExame;

public class ConversorCadastroDeExames implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
		CadastroDeExame cadastroDeExame = new CadastroDeExame();
		
		try
		{
			cadastroDeExame = cadastroDeExameDAO.buscarCadastroDeExamePorID(Integer.parseInt(arg2));
		}
		
		catch(Exception e){}
		return cadastroDeExame;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		CadastroDeExame cadastroDeExame = (CadastroDeExame)arg2;
		return String.valueOf(cadastroDeExame.getCAD_EXAME_ID());
	}

}
