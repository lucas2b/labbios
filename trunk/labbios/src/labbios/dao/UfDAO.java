package labbios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.db.DatabaseUtil;
import labbios.dto.Uf;

public class UfDAO extends DatabaseUtil{

	public List<Uf> listarEstados() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from UF");
		List<Uf> listaEstado = new LinkedList<Uf>();
		
		while(rs.next())
		{
			Uf uf = new Uf();
			uf.setUF_ID(rs.getInt("UF_ID"));
			uf.setUF_NOME(rs.getString("UF_NOME"));
			listaEstado.add(uf);
		}
		
		return listaEstado;
	}
	
	public Uf procurarEstadoPorID(int estadoID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from UF where UF_ID="+estadoID);
		rs.next();
		
		Uf uf = new Uf();
		uf.setUF_ID(rs.getInt("UF_ID"));
		uf.setUF_NOME(rs.getString("UF_NOME"));
		
		return uf;
	}
	
	public List<SelectItem> listarEstadosCombo() throws ClassNotFoundException, SQLException
	{
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Uf uf: listarEstados())
		{
			toReturn.add(new SelectItem(uf, uf.getUF_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;	
	}
}
