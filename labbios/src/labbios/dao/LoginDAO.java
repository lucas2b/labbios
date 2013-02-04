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
		
		return retorno;
	}
	
	
	public boolean validarLogin(Login login) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Select * from LOGIN where LOGIN_NOME=? and LOGIN_SENHA=?");
		ps.setString(1, login.getLOGIN_NOME());
		ps.setString(2, login.getLOGIN_SENHA());
		
		ResultSet rs = ps.executeQuery();
		boolean retorno = rs.next();
		
		rs.close();
		ps.close();
		
		return retorno;
	}
	
	public Login buscarLoginPorID(int loginID) throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps = getPreparedStatement("Select * from LOGIN where LOGIN_ID=?");
		ps.setInt(1, loginID);
		ResultSet rs = ps.executeQuery();
		
		Login login = null;
		while(rs.next()){
			login = new Login();
			popularLogin(rs, login);
		}
		
		return login;
	}

	
	public List<Login> listarUsuarios() throws ClassNotFoundException, SQLException
	{
		List<Login> listaDeUsuarios = new LinkedList<Login>();
		ResultSet rs = getStatement().executeQuery("Select * from LOGIN");
		
		while(rs.next())
		{
			Login login = new Login();
			popularLogin(rs, login);
			listaDeUsuarios.add(login);
		}
		
		return listaDeUsuarios;
	}
	
	public void popularLogin(ResultSet rs, Login login) throws SQLException
	{
		login.setLOGIN_ID(rs.getInt("LOGIN_ID"));
		login.setLOGIN_NOME(rs.getString("LOGIN_NOME"));
		login.setLOGIN_SENHA(rs.getString("LOGIN_SENHA"));

	}

}
