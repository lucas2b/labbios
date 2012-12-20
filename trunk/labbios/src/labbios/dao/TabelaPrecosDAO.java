package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import labbios.db.DatabaseUtil;
import labbios.db.TabelaPrecos;

public class TabelaPrecosDAO extends DatabaseUtil{
	
	private ConvenioDAO convenioDAO = new ConvenioDAO();
	private CadastroDeExameDAO cadastroDeExameDAO= new CadastroDeExameDAO();
	
	public boolean adicionarValor(TabelaPrecos tabelaPrecos) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into TABELA_PRECOS set TAB_PRECOS_VALOR=?," +
																					" CONVENIO_ID=?," +
																					" CAD_EXAME_ID=?," +
																					" TAB_PRECOS_CAT_IPE=?");
		
		ps.setDouble(1, tabelaPrecos.getTAB_PRECOS_VALOR());
		ps.setInt(2, tabelaPrecos.getCONVENIO().getCONVENIO_ID());
		ps.setInt(3, tabelaPrecos.getEXAME().getEXAME_ID());
		ps.setString(4, String.valueOf(tabelaPrecos.getTAB_PRECOS_CAT_IPE()));
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
		
	}

}
