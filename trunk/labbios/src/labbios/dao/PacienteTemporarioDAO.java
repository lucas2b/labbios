package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.PacienteTemporario;

public class PacienteTemporarioDAO extends DatabaseUtil{
	
	public boolean adicionarPacienteTemporario(PacienteTemporario pacienteTemporario) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into PACIENTE_TEMPORARIO set PACIENTE_TEMPORARIO_NOME=?," +
																						" PACIENTE_TEMPORARIO_DT_NASCIMENTO=?," +
																						" PACIENTE_TEMPORARIO_SEXO=?," +
																						" PACIENTE_TEMPORARIO_OBS=?");
		ps.setString(1, pacienteTemporario.getPACIENTE_TEMPORARIO_NOME());
		ps.setDate(2, pacienteTemporario.getPACIENTE_TEMPORARIO_DT_NASCIMENTO());
		ps.setString(3, String.valueOf(pacienteTemporario.getPACIENTE_TEMPORARIO_SEXO()));
		ps.setString(4, pacienteTemporario.getPACIENTE_TEMPORARIO_OBS());
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
	}
	
	public boolean editarPacienteTemporario(PacienteTemporario pacienteTemporario) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Update PACIENTE_TEMPORARIO set PACIENTE_TEMPORARIO_NOME=?," +
																						" PACIENTE_TEMPORARIO_DT_NASCIMENTO=?," +
																						" PACIENTE_TEMPORARIO_SEXO=?," +
																						" PACIENTE_TEMPORARIO_OBS=? " +
																						" where PACIENTE_TEMPORARIO_ID=?");
		ps.setString(1, pacienteTemporario.getPACIENTE_TEMPORARIO_NOME());
		ps.setDate(2, pacienteTemporario.getPACIENTE_TEMPORARIO_DT_NASCIMENTO());
		ps.setString(3, String.valueOf(pacienteTemporario.getPACIENTE_TEMPORARIO_SEXO()));
		ps.setString(4, pacienteTemporario.getPACIENTE_TEMPORARIO_OBS());
		ps.setInt(5, pacienteTemporario.getPACIENTE_TEMPORARIO_ID());
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
	}
	
	public List<PacienteTemporario> listarPacientesTemporarios() throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Select * from PACIENTE_TEMPORARIO");
		ResultSet rs = ps.executeQuery();
		List<PacienteTemporario> listaPacientesTemporarios = new LinkedList<PacienteTemporario>();
		
		while(rs.next())
		{
			listaPacientesTemporarios.add(popularPacienteTemporario(rs));
		}
		
		return listaPacientesTemporarios;
	}
	
	public PacienteTemporario procurarPacienteTemporarioPorID(int temporarioID) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Select * from PACIENTE_TEMPORARIO where PACIENTE_TEMPORARIO_ID=?");
		ResultSet rs = ps.executeQuery();
		rs.next();
		return popularPacienteTemporario(rs);
	}
	
	public PacienteTemporario popularPacienteTemporario(ResultSet rs) throws SQLException
	{
		PacienteTemporario pacienteTemporario = new PacienteTemporario();
		pacienteTemporario.setPACIENTE_TEMPORARIO_ID(rs.getInt("PACIENTE_TEMPORARIO_ID"));
		pacienteTemporario.setPACIENTE_TEMPORARIO_NOME(rs.getString("PACIENTE_TEMPORARIO_NOME"));
		pacienteTemporario.setPACIENTE_TEMPORARIO_DT_NASCIMENTO(rs.getDate("PACIENTE_TEMPORARIO_DT_NASCIMENTO"));
		pacienteTemporario.setPACIENTE_TEMPORARIO_SEXO(rs.getString("PACIENTE_TEMPORARIO_SEXO").charAt(0));
		pacienteTemporario.setPACIENTE_TEMPORARIO_OBS(rs.getString("PACIENTE_TEMPORARIO_OBS"));
		return pacienteTemporario;
	}

}
