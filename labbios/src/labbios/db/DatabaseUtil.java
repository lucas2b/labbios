package labbios.db;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;


public class DatabaseUtil {
	
	private static Connection connection;
	
	//Sempre retorna uma conexão, se não existe ativa cria uma
	public static Connection getConnection() throws SQLException, ClassNotFoundException
	{
		if(connection == null)
		{
			System.out.println("Nova conexão realizada");
			Class.forName("com.mysql.jdbc.Driver");
			//connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/labbiosv2", "root", "root");
			connection = DriverManager.getConnection("jdbc:mysql://dbmy0023.whservidor.com/laboratori10_2", "laboratori10_2", "ghti3847");
			//System.out.println("##Conexão = "+connection.toString());
		}
		return connection;		
	}
	
	
	
	
	/*
	 * Tanto o Objeto Statement quanto o PreparedStatement necessitam ser
	 * esecutados pelo método execute() e retornam um Statement de resposta
	 */
	
	public Statement getStatement() throws ClassNotFoundException, SQLException
	{
			return getConnection().createStatement();
			//Passa o SQL já montado com os valores e retorna o resultado da conexão
	}
	
	
	
	//Previne SQL Injection
	public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException
	{
		return getConnection().prepareStatement(sql);
	}
	
	public void closeAll() throws SQLException
	{
		if(connection != null){
			connection.close();
		}
	}
}
