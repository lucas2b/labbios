package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.db.DatabaseUtil;
import labbios.dto.Medico;
import labbios.dto.Paciente;


public class MedicoDAO extends DatabaseUtil{
	
	CidadeDAO cidadeDAO = new CidadeDAO();
	
	public boolean adicionarMedico(Medico medico) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		
		PreparedStatement ps = getPreparedStatement("Insert into MEDICO set MEDICO_NOME=?," +
																			" MEDICO_ESPECIALIDADE=?," +
																			" MEDICO_CRM=?," +
																			" MEDICO_ENDERECO=?," +
																			" MEDICO_BAIRRO=?," +
																			" MEDICO_CEP=?," +
																			" MEDICO_EMAIL=?," +
																			" MEDICO_FONE=?," +
																			" MEDICO_CELULAR=?," +
																			" MEDICO_OBS=?," +
																			" CIDADE_ID=?");
		ps.setString(1, medico.getMEDICO_NOME());
		ps.setString(2, medico.getMEDICO_ESPECIALIDADE());
		ps.setString(3, medico.getMEDICO_CRM());
		ps.setString(4, medico.getMEDICO_ENDERECO());
		ps.setString(5, medico.getMEDICO_BAIRRO());
		ps.setString(6, medico.getMEDICO_CEP());
		ps.setString(7, medico.getMEDICO_EMAIL());
		ps.setString(8, medico.getMEDICO_FONE());
		ps.setString(9, medico.getMEDICO_CELULAR());
		ps.setString(10, medico.getMEDICO_OBS());
		ps.setInt(11, medico.getCIDADE().getCIDADE_ID());
		
		retorno = ps.execute();
		
		return retorno;
	}
	
	public boolean editarMedico(Medico medico) throws ClassNotFoundException, SQLException
	{
				boolean retorno = false;
		
				PreparedStatement ps = getPreparedStatement("Update MEDICO set MEDICO_NOME=?," +
																			" MEDICO_ESPECIALIDADE=?," +
																			" MEDICO_CRM=?," +
																			" MEDICO_ENDERECO=?," +
																			" MEDICO_BAIRRO=?," +
																			" MEDICO_CEP=?," +
																			" MEDICO_EMAIL=?," +
																			" MEDICO_FONE=?," +
																			" MEDICO_CELULAR=?," +
																			" MEDICO_OBS=?," +
																			" CIDADE_ID=? " +
																			" where MEDICO_ID=?");
		ps.setString(1, medico.getMEDICO_NOME());
		ps.setString(2, medico.getMEDICO_ESPECIALIDADE());
		ps.setString(3, medico.getMEDICO_CRM());
		ps.setString(4, medico.getMEDICO_ENDERECO());
		ps.setString(5, medico.getMEDICO_BAIRRO());
		ps.setString(6, medico.getMEDICO_CEP());
		ps.setString(7, medico.getMEDICO_EMAIL());
		ps.setString(8, medico.getMEDICO_FONE());
		ps.setString(9, medico.getMEDICO_CELULAR());
		ps.setString(10, medico.getMEDICO_OBS());
		ps.setInt(11, medico.getCIDADE().getCIDADE_ID());
		ps.setInt(12, medico.getMEDICO_ID());
		
		retorno = ps.execute();
		
		return retorno;
	}
	
	public List<Medico> listarMedicos() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from MEDICO");
		List<Medico> listaMedico = new LinkedList<Medico>();
		
		while(rs.next())
		{
			listaMedico.add(popularMedico(rs));
		}
		
		return listaMedico;
	}
	
	public Medico procurarMedicoPorID(int medicoID) throws ClassNotFoundException, SQLException
	{
		PreparedStatement ps = getPreparedStatement("Select * from MEDICO where MEDICO_ID="+medicoID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		return popularMedico(rs);
	}
	
	public Medico popularMedico(ResultSet rs) throws SQLException, ClassNotFoundException
	{
		Medico medico = new Medico();
		medico.setMEDICO_ID(rs.getInt("MEDICO_ID"));
		medico.setMEDICO_NOME(rs.getString("MEDICO_NOME"));
		medico.setMEDICO_ESPECIALIDADE(rs.getString("MEDICO_ESPECIALIDADE"));
		medico.setMEDICO_CRM(rs.getString("MEDICO_CRM"));
		medico.setMEDICO_ENDERECO(rs.getString("MEDICO_ENDERECO"));
		medico.setMEDICO_BAIRRO(rs.getString("MEDICO_BAIRRO"));
		medico.setMEDICO_CEP(rs.getString("MEDICO_CEP"));
		medico.setMEDICO_EMAIL(rs.getString("MEDICO_EMAIL"));
		medico.setMEDICO_FONE(rs.getString("MEDICO_FONE"));
		medico.setMEDICO_CELULAR(rs.getString("MEDICO_CELULAR"));
		medico.setMEDICO_OBS(rs.getString("MEDICO_OBS"));
		medico.setCIDADE(cidadeDAO.procurarCidadePorID(rs.getInt("CIDADE_ID")));
		
		return medico;
	}
	
	public List<SelectItem> getComboMedicos() throws ClassNotFoundException, SQLException 
	{		
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Medico medico : listarMedicos())
		{
			toReturn.add(new SelectItem(medico, medico.getMEDICO_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;
	}
}
