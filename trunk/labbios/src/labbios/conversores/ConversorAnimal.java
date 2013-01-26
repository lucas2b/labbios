package labbios.conversores;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import labbios.dao.AnimalDAO;
import labbios.dto.Animal;

public class ConversorAnimal implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		
		AnimalDAO animalDAO = new AnimalDAO();
		Animal animal = null;	
		
		try
		{
			animal =  animalDAO.procurarAnimalPorID(Integer.parseInt(arg2));
		}
		
		catch(Exception e){
			e.printStackTrace();
		}
		return animal;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		Animal animal = (Animal)arg2;
		return String.valueOf(animal.getANIMAL_ID());
	}

}
