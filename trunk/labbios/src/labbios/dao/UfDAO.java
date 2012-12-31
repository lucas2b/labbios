package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

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
		PreparedStatement ps = getPreparedStatement("Select * from UF where UF_ID=?");
		ps.setInt(1, estadoID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		Uf uf = new Uf();
		uf.setUF_ID(rs.getInt("UF_ID"));
		uf.setUF_NOME(rs.getString("UF_NOME"));
		
		return uf;
	}
	

}
