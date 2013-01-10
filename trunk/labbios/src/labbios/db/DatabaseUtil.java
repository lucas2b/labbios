package labbios.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class DatabaseUtil {
	
	private static Connection connection;
	
	//Sempre retorna uma conex�o, se n�o existe ativa cria uma
	public Connection getConnection() throws SQLException, ClassNotFoundException
	{
		if(connection == null)
		{
			System.out.println("Nova conex�o realizada");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/labbiosV2", "root", "root");
		}
		return connection;		
	}
	
	
	
	
	/*
	 * Tanto o Objeto Statement quanto o PreparedStatement necessitam ser
	 * esecutados pelo m�todo execute() e retornam um Statement de resposta
	 */
	
	public Statement getStatement() throws ClassNotFoundException, SQLException
	{
			return getConnection().createStatement();
			//Passa o SQL j� montado com os valores e retorna o resultado da conex�o
	}
	
	
	
	//Previne SQL Injection
	public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException
	{
		return getConnection().prepareStatement(sql);
	}
	
	public void closeAll() throws SQLException
	{
		if(connection != null)
			connection.close();
	}
}
