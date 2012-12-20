package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.Convenio;
import labbios.db.DatabaseUtil;

public class ConvenioDAO extends DatabaseUtil{
	
	public boolean adicionarConvenio(Convenio convenio) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		retorno = getStatement().execute("Insert into CONVENIO set CONVENIO_NOME="+convenio.getCONVENIO_ID());
		getStatement().close();
		return retorno;
	}
	
	public Convenio buscarConvenioPorID(int convenioID) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Select * from CONVENIO where CONVENIO_ID=?");
		ps.setInt(1, convenioID);
		
		ResultSet rs = ps.executeQuery();
		Convenio convenioRetorno = popularConvenio(rs);
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
	
	

}
