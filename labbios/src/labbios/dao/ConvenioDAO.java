package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.db.DatabaseUtil;
import labbios.dto.CadastroDeExame;
import labbios.dto.Convenio;

public class ConvenioDAO extends DatabaseUtil{
	
	public boolean adicionarConvenio(Convenio convenio) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into CONVENIO set CONVENIO_NOME=?");
		ps.setString(1, convenio.getCONVENIO_NOME());
		retorno = ps.execute();
		return retorno;
	}
	
	public Convenio buscarConvenioPorID(int convenioID) throws ClassNotFoundException, SQLException
	{	
		ResultSet rs = getStatement().executeQuery("Select * from CONVENIO where CONVENIO_ID="+convenioID);
		Convenio convenioRetorno = null;
		if(rs.next())
		{
			convenioRetorno = popularConvenio(rs);			
		}
		return convenioRetorno;
	}
	
	public List<Convenio> listarConvenios() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from CONVENIO");
		List<Convenio> listaConvenios = new LinkedList<Convenio>();
		
		while(rs.next())
		{
			Convenio convenio = new Convenio();
			convenio = popularConvenio(rs);
			listaConvenios.add(convenio);
		}
		
		return listaConvenios;
		
	}
	
	public Convenio popularConvenio(ResultSet rs) throws SQLException
	{
		Convenio convenio = new Convenio();
		convenio.setCONVENIO_ID(rs.getInt("CONVENIO_ID"));
		convenio.setCONVENIO_NOME(rs.getString("CONVENIO_NOME"));
		return convenio;
	}
	
	public List<SelectItem> getComboConvenios() throws ClassNotFoundException, SQLException 
	{		
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Convenio convenio : listarConvenios())
		{
			toReturn.add(new SelectItem(convenio, convenio.getCONVENIO_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;
	}

}
