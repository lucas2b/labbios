package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.db.DatabaseUtil;
import labbios.dto.Animal;

public class AnimalDAO extends DatabaseUtil{
	
	public boolean adicionarAnimal(Animal animal) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into ANIMAL set ANIMAL_NOME=?, " +
																			"ANIMAL_ESPECIE=?, " +
																			"ANIMAL_DT_NASCIMENTO=?, " +
																			"ANIMAL_PROPRIETARIO=?, " +
																			"ANIMAL_FONE=?");
		ps.setString(1, animal.getANIMAL_NOME());
		ps.setString(2, animal.getANIMAL_ESPECIE());
		ps.setDate(3, animal.getANIMAL_DT_NASCIMENTO());
		ps.setString(4, animal.getANIMAL_PROPRIETARIO());
		ps.setString(5, animal.getANIMAL_FONE());
		
		
		retorno = ps.execute();
		return retorno;
	}
	
	public boolean editarAnimal(Animal animal) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Update ANIMAL set ANIMAL_NOME=?, " +
																			"ANIMAL_ESPECIE=?, " +
																			"ANIMAL_DT_NASCIMENTO=?, " +
																			"ANIMAL_PROPRIETARIO=?, " +
																			"ANIMAL_FONE=? " +
																			"where ANIMAL_ID=?");
		
		ps.setString(1, animal.getANIMAL_NOME());
		ps.setString(2, animal.getANIMAL_ESPECIE());
		ps.setDate(3, animal.getANIMAL_DT_NASCIMENTO());
		ps.setString(4, animal.getANIMAL_PROPRIETARIO());
		ps.setString(5, animal.getANIMAL_FONE());
		ps.setInt(6, animal.getANIMAL_ID());
		
		
		retorno = ps.execute();
		return retorno;
	}
	
	public List<Animal> listarAnimais() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from ANIMAL");
		List<Animal> listaAnimais = new LinkedList<Animal>();
		
		while(rs.next())
		{
			listaAnimais.add(popularAnimal(rs));
		}
		
		return listaAnimais;
	}
	
	public Animal procurarAnimalPorID(int animalID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from ANIMAL where ANIMAL_ID="+animalID);
		rs.next();
		return popularAnimal(rs);
	}
	
	public Animal popularAnimal(ResultSet rs) throws SQLException
	{
		Animal animal = new Animal();
		animal.setANIMAL_ID(rs.getInt("ANIMAL_ID"));
		animal.setANIMAL_NOME(rs.getString("ANIMAL_NOME"));
		animal.setANIMAL_FONE(rs.getString("ANIMAL_FONE"));
		animal.setANIMAL_PROPRIETARIO(rs.getString("ANIMAL_PROPRIETARIO"));
		animal.setANIMAL_ESPECIE(rs.getString("ANIMAL_ESPECIE"));
		animal.setANIMAL_DT_NASCIMENTO(rs.getDate("ANIMAL_DT_NASCIMENTO"));
		return animal;
	}
	
	
	public List<SelectItem> getComboAnimais() throws ClassNotFoundException, SQLException 
	{		
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Animal animal : listarAnimais())
		{
			toReturn.add(new SelectItem(animal, animal.getANIMAL_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;
	}

}
