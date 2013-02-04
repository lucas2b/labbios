package labbios.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.db.DatabaseUtil;
import labbios.dto.Cidade;
import labbios.dto.Paciente;

public class PacienteDAO extends DatabaseUtil {
	
	CidadeDAO cidadeDAO = new CidadeDAO();
	Paciente paciente;
	
	public PacienteDAO() {
		//paciente = new Paciente();
	}
	
	public boolean adicionarPaciente(Paciente paciente)	{
		Connection conn = null;
		PreparedStatement ps = null;
		
		try{
			try{
				
				conn = getConnection();
				ps = getPreparedStatement("Insert into PACIENTE set PACIENTE_NOME=?," +
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
																					" PACIENTE_RESPONSAVEL=? ");
				System.out.println(paciente.getPACIENTE_NOME());
				
				ps.setString(1, paciente.getPACIENTE_NOME());
				ps.setString(2, String.valueOf(paciente.getPACIENTE_SEXO()) );
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
				ps.setDate(14,  new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
				ps.setInt(15, paciente.getCIDADE().getCIDADE_ID());
				ps.setString(16, paciente.getPACIENTE_RESPONSAVEL());
				
				ps.executeUpdate();
				return true;
			
			
			}finally{
				if(ps != null){
					ps.close();
				}
				if(conn != null){
					conn.close();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	public boolean editarPaciente(Paciente paciente) throws ClassNotFoundException, SQLException
	{
		
		PreparedStatement ps = getPreparedStatement("Update PACIENTE set PACIENTE_NOME=?," +
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
																			" PACIENTE_RESPONSAVEL=? " +
																			" where PACIENTE_ID=?");
		ps.setString(1, paciente.getPACIENTE_NOME());
		ps.setString(2, String.valueOf(paciente.getPACIENTE_SEXO()) );
		ps.setString(3, paciente.getPACIENTE_CPF());
		ps.setString(4, paciente.getPACIENTE_RG());
		ps.setDate(5, new java.sql.Date(Calendar.getInstance().getTimeInMillis()));
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
		ps.setInt(17, paciente.getPACIENTE_ID());
		
		boolean retorno = ps.execute();
		return retorno;
	}
	
	
	
	public Paciente procurarPacientePorID(int pacienteID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from PACIENTE where PACIENTE_ID="+pacienteID);
		rs.next();
		return popularPaciente(rs);
	}
	
	public List<String> procurarPacientePorNome(String pacienteNome) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select PACIENTE_NOME from PACIENTE where PACIENTE_NOME LIKE "+pacienteNome);
		List<String> listaPacientes = new ArrayList<String>();
		
		if(rs != null)
		{
			while(rs.next())
			{
				listaPacientes.add(rs.getString("PACIENTE_NOME"));
			}
		}
		
		return listaPacientes;
	}
	
	public List<Paciente> listarPacientes() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from PACIENTE");
		List<Paciente> listaPacientes = new LinkedList<Paciente>();
		
		while(rs.next())
		{
			Paciente paciente = new Paciente();
			paciente = popularPaciente(rs);
			listaPacientes.add(paciente);
		}
		
		rs.close();
		
		return listaPacientes;
	}

	private Paciente popularPaciente(ResultSet rs) throws SQLException, ClassNotFoundException {
		Paciente paciente = new Paciente();
		paciente.setPACIENTE_ID(rs.getInt("PACIENTE_ID"));
		paciente.setPACIENTE_NOME(rs.getString("PACIENTE_NOME"));
		paciente.setPACIENTE_SEXO(rs.getString("PACIENTE_SEXO").charAt(0));
		paciente.setPACIENTE_CPF(rs.getString("PACIENTE_CPF"));
		paciente.setPACIENTE_RG(rs.getString("PACIENTE_RG"));
		paciente.setPACIENTE_DT_NASCIMENTO(rs.getDate("PACIENTE_DT_NASC"));
		paciente.setPACIENTE_FONE(rs.getString("PACIENTE_FONE"));
		paciente.setPACIENTE_CELULAR(rs.getString("PACIENTE_CELULAR"));
		paciente.setPACIENTE_ENDERECO(rs.getString("PACIENTE_ENDERECO"));
		paciente.setPACIENTE_BAIRRO(rs.getString("PACIENTE_BAIRRO"));
		paciente.setPACIENTE_CEP(rs.getString("PACIENTE_CEP"));
		paciente.setPACIENTE_EMAIL(rs.getString("PACIENTE_EMAIL"));
		paciente.setPACIENTE_OBS_MEDICACOES(rs.getString("PACIENTE_OBS_MEDICACOES"));
		paciente.setPACIENTE_OBS_CONVENIOS(rs.getString("PACIENTE_OBS_CONVENIOS"));
		paciente.setPACIENTE_DT_CRIACAO(rs.getDate("PACIENTE_DT_CRIACAO"));
		paciente.setCIDADE(cidadeDAO.procurarCidadePorID(rs.getInt("CIDADE_ID")));
		paciente.setPACIENTE_RESPONSAVEL(rs.getString("PACIENTE_RESPONSAVEL"));
		
		return paciente;
	}
	
	public List<SelectItem> getComboPacientes() throws ClassNotFoundException, SQLException 
	{		
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Paciente paciente : listarPacientes())
		{
			toReturn.add(new SelectItem(paciente, paciente.getPACIENTE_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;
	}
	

}
