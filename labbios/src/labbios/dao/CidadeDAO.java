package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.db.DatabaseUtil;
import labbios.dto.Cidade;
import labbios.dto.MaterialExame;
import labbios.dto.PacienteTemporario;

public class CidadeDAO extends DatabaseUtil{
	
	UfDAO ufDAO = new UfDAO();
	
	public boolean adicionarCidade(Cidade cidade) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into CIDADE set CIDADE_NOME=?, UF_ID=?");
		ps.setString(1, cidade.getCIDADE_NOME());
		ps.setInt(2, cidade.getUF().getUF_ID());
		retorno = ps.execute();

		return retorno;
	}
	
	public boolean editarCidade(Cidade cidade) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Update CIDADE set CIDADE_NOME=?, UF_ID=? where CIDADE_ID=?");
		ps.setString(1, cidade.getCIDADE_NOME());
		ps.setInt(2, cidade.getUF().getUF_ID());
		ps.setInt(3, cidade.getCIDADE_ID());
		retorno = ps.execute();
		
		return retorno;
	}
	
	public List<Cidade> listarCidades() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from CIDADE");
		List<Cidade> listaCidades = new LinkedList<Cidade>();
		
		while(rs.next())
		{
			listaCidades.add(popularCidade(rs));
		}
		
		return listaCidades;
	}
	
	public Cidade procurarCidadePorID(int cidadeID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from CIDADE where CIDADE_ID="+cidadeID);
		rs.next();
		return popularCidade(rs);
	}
	
	public List<SelectItem> listarCidadesCombo() throws ClassNotFoundException, SQLException
	{
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Cidade cidade : listarCidades())
		{
			toReturn.add(new SelectItem(cidade, cidade.getCIDADE_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;	
	}
	
	public Cidade popularCidade(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		Cidade cidade = new Cidade();
		cidade.setCIDADE_ID(rs.getInt("CIDADE_ID"));
		cidade.setCIDADE_NOME(rs.getString("CIDADE_NOME"));
		cidade.setUF(ufDAO.procurarEstadoPorID(rs.getInt("UF_ID")));
		return cidade;
	}
	
	

}
