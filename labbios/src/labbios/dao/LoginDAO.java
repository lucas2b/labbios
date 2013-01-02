package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.Login;

public class LoginDAO extends DatabaseUtil{
	
	public boolean adicionarUsuario(Login login) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into LOGIN set LOGIN_NOME=?, LOGIN_SENHA=?");
		ps.setString(1,login.getLOGIN_NOME());
		ps.setString(2, login.getLOGIN_SENHA());
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
	}
	
	
	public boolean validarLogin(Login login) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Select * from LOGIN where LOGIN_NOME=? and LOGIN_SENHA=?");
		ps.setString(1, login.getLOGIN_NOME());
		ps.setString(2, login.getLOGIN_SENHA());
		
		ResultSet rs = ps.executeQuery();
		retorno = rs.next();
		rs.close();
		
		return retorno;
	}
	
	public Login buscarLoginPorID(int loginID) throws SQLException, ClassNotFoundException
	{
		ResultSet rs = getStatement().executeQuery("Select * from LOGIN where LOGIN_ID=?"+loginID);
		Login retornoLogin = null;
		
		if(rs.next())
		{
			retornoLogin = popularLogin(rs);
		}
		
		rs.close();
		return retornoLogin;
	}

	
	public List<Login> listarUsuarios() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from LOGIN");
		List<Login> listaDeUsuarios = new LinkedList<Login>();
		
		while(rs.next())
		{
			Login login = popularLogin(rs);
			listaDeUsuarios.add(login);
		}
		
		return listaDeUsuarios;
	}
	
	public Login popularLogin(ResultSet rs) throws SQLException
	{
		Login login = new Login();
		login.setLOGIN_NOME(rs.getString("LOGIN_NOME"));
		login.setLOGIN_SENHA(rs.getString("LOGIN_SENHA"));
		return login;
	}

}
