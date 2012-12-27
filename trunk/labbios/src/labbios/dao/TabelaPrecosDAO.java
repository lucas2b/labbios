package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.TabelaPrecos;

public class TabelaPrecosDAO extends DatabaseUtil{
	
	private ConvenioDAO convenioDAO = new ConvenioDAO();
	private CadastroDeExameDAO cadastroDeExameDAO= new CadastroDeExameDAO();
	
	public boolean adicionarValor(TabelaPrecos tabelaPrecos) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into TABELA_PRECOS set  TAB_PRECOS_VALOR=?," +
																					" CONVENIO_ID=?," +
																					" CAD_EXAME_ID=?," +
																					" TAB_PRECOS_CAT_IPE=?");
		
		ps.setDouble(1, tabelaPrecos.getTAB_PRECOS_VALOR());
		ps.setInt(2, tabelaPrecos.getCONVENIO().getCONVENIO_ID());
		ps.setInt(3, tabelaPrecos.getEXAME().getCAD_EXAME_ID());
		ps.setString(4, String.valueOf(tabelaPrecos.getTAB_PRECOS_CAT_IPE()));
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
	}
	
	public boolean editarValor(TabelaPrecos tabelaPrecos) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Update TABELA_PRECOS set  TAB_PRECOS_VALOR=?," +
																				" CONVENIO_ID=?," +
																				" CAD_EXAME_ID=?," +
																				" TAB_PRECOS_CAT_IPE=?" +
																				"where TAB_PRECOS_ID=?");

		ps.setDouble(1, tabelaPrecos.getTAB_PRECOS_VALOR());
		ps.setInt(2, tabelaPrecos.getCONVENIO().getCONVENIO_ID());
		ps.setInt(3, tabelaPrecos.getEXAME().getCAD_EXAME_ID());
		ps.setString(4, String.valueOf(tabelaPrecos.getTAB_PRECOS_CAT_IPE()));
		ps.setInt(5, tabelaPrecos.getTAB_PRECOS_ID());
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
		
	}
	
	public List<TabelaPrecos> listarTabelaDePrecos() throws SQLException, ClassNotFoundException
	{
		PreparedStatement ps = getPreparedStatement("Select * from TABELA_PRECOS");
		List<TabelaPrecos> listaTabelaDePrecos = new LinkedList<TabelaPrecos>();
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			TabelaPrecos tabelaPrecos = new TabelaPrecos();
			tabelaPrecos.setTAB_PRECOS_VALOR(rs.getDouble("TAB_PRECOS_VALOR"));
			tabelaPrecos.setCONVENIO(convenioDAO.buscarConvenioPorID(rs.getInt("CONVENIO_ID")));
			tabelaPrecos.setEXAME(cadastroDeExameDAO.buscarCadastroDeExamePorID(rs.getInt("CAD_EXAME_ID")));
			tabelaPrecos.setTAB_PRECOS_CAT_IPE(rs.getString("TAB_PRECOS_CAT_IPE").charAt(0));
			
			listaTabelaDePrecos.add(tabelaPrecos);
		}
		
		return listaTabelaDePrecos;
	}

}
