package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

import labbios.db.DatabaseUtil;
import labbios.db.Paciente;

public class PacienteDAO extends DatabaseUtil {
	
	public boolean adicionarPaciente(Paciente paciente) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Insert into PACIENTE set PACIENTE_NOME=?," +
																			" PACIENTE_SEXO=?," +
																			" PACIENTE_CPF=?," +
																			" PACIENTE_RG=?," +
																			" PACIENTE_DT_NASC=?," +
																			" PACIENTE_FONE=?," +
																			" PACIENTE_CELULAR=?," +
																			" PACIENTE_ENDERECO=?," +
																			" PACIENTE_BAIRRO=?," +
																			" PACIENTE_CEP=?," +
																			" PACIENTE_EMAIL=?," +
																			" PACIENTE_OBS_MEDICACOES=?," +
																			" PACIENTE_OBS_CONVENIOS=?," +
																			" PACIENTE_DT_CRIACAO=?," +
																			" CIDADE_ID=?," +
																			" PACIENTE_RESPONSAVEL=?");
		ps.setString(1, paciente.getPACIENTE_NOME());
		ps.setString(2, String.valueOf(paciente.getPACIENTE_SEXO()));
		ps.setString(3, paciente.getPACIENTE_CPF());
		ps.setString(4, paciente.getPACIENTE_RG());
		ps.setDate(5, paciente.getPACIENTE_DT_NASCIMENTO());
		ps.setString(6, paciente.getPACIENTE_FONE());
		ps.setString(7, paciente.getPACIENTE_CELULAR());
		ps.setString(8, paciente.getPACIENTE_ENDERECO());
		ps.setString(9, paciente.getPACIENTE_BAIRRO());
		ps.setString(10, paciente.getPACIENTE_CEP());
		ps.setString(11, paciente.getPACIENTE_EMAIL());
		ps.setString(12, paciente.getPACIENTE_OBS_MEDICACOES());
		ps.setString(13, paciente.getPACIENTE_OBS_CONVENIOS());
		ps.setDate(14, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
		ps.setInt(15, paciente.getCIDADE().getCIDADE_ID());
		ps.setString(16, paciente.getPACIENTE_RESPONSAVEL());
		
		boolean retorno = ps.execute();
		ps.close();
		return retorno;
	}

}
