package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.CadastroDeExame;

public class CadastroDeExameDAO extends DatabaseUtil{
	
	GrupoExameDAO grupoExameDAO = new GrupoExameDAO();
	MaterialExameDAO materialExameDAO = new MaterialExameDAO();
	TipoLaboratorioDAO tipoLaboratorioDAO = new TipoLaboratorioDAO();
	
	public CadastroDeExame buscarCadastroDeExamePorID(int cadastroDeExameID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from CADASTRO_DE_EXAME where CAD_EXAME_ID="+cadastroDeExameID);
		CadastroDeExame cadastroDeExameRetorno = null;
		
		if(rs.next())
		{
			cadastroDeExameRetorno = popularCadastroDeExame(rs);
		}
		
		return cadastroDeExameRetorno;
	}
	
	public boolean adicionarNovoExame(CadastroDeExame cadastroDeExame) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		
		PreparedStatement ps = getPreparedStatement("Insert into CADASTRO_EXAMES set CAD_EXAME_ABREVIATURA=?," +
																					" CAD_EXAME_NOME=?," +
																					" GRUPO_EXAME_ID=?," +
																					" MATERIAL_EXAME_ID=?," +
																					" TIPO_LABORATORIO_ID=?," +
																					" CAD_EXAME_DIAS_ENTREGA=?," +
																					" CAD_EXAME_COD_SUS=?," +
																					" CAD_EXAME_GRUPO_ETIQUETA=?," +
																					" CAD_EXAME_TIPO_ENTRADA=?");
		ps.setString(1, cadastroDeExame.getCAD_EXAME_ABREVIATURA());
		ps.setString(2, cadastroDeExame.getCAD_EXAME_NOME());
		ps.setInt(3, cadastroDeExame.getGRUPO_EXAME().getGRUPO_EXAME_ID());
		ps.setInt(4, cadastroDeExame.getTIPO_LABORATORIO().getTIPO_LABORATORIO_ID());
		ps.setString(5, cadastroDeExame.getCAD_EXAME_DIAS_ENTREGA());
		ps.setString(6, cadastroDeExame.getCAD_EXAME_COD_SUS());
		ps.setInt(7, cadastroDeExame.getCAD_EXAME_GRUPO_ETIQUETA());
		ps.setString(8, String.valueOf(cadastroDeExame.getCAD_EXAME_TIPO_ENTRADA()));
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
		
	}
	
	public boolean editarCadastroDeExame(CadastroDeExame cadastroDeExame) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		
		PreparedStatement ps = getPreparedStatement("Update CADASTRO_EXAMES set CAD_EXAME_ABREVIATURA=?," +
																					" CAD_EXAME_NOME=?," +
																					" GRUPO_EXAME_ID=?," +
																					" MATERIAL_EXAME_ID=?," +
																					" TIPO_LABORATORIO_ID=?," +
																					" CAD_EXAME_DIAS_ENTREGA=?," +
																					" CAD_EXAME_COD_SUS=?," +
																					" CAD_EXAME_GRUPO_ETIQUETA=?," +
																					" CAD_EXAME_TIPO_ENTRADA=? " +
																					"where CAD_EXAME_ID=?");
		
		ps.setString(1, cadastroDeExame.getCAD_EXAME_ABREVIATURA());
		ps.setString(2, cadastroDeExame.getCAD_EXAME_NOME());
		ps.setInt(3, cadastroDeExame.getGRUPO_EXAME().getGRUPO_EXAME_ID());
		ps.setInt(4, cadastroDeExame.getTIPO_LABORATORIO().getTIPO_LABORATORIO_ID());
		ps.setString(5, cadastroDeExame.getCAD_EXAME_DIAS_ENTREGA());
		ps.setString(6, cadastroDeExame.getCAD_EXAME_COD_SUS());
		ps.setInt(7, cadastroDeExame.getCAD_EXAME_GRUPO_ETIQUETA());
		ps.setString(8, String.valueOf(cadastroDeExame.getCAD_EXAME_TIPO_ENTRADA()));
		ps.setInt(9, cadastroDeExame.getCAD_EXAME_ID());
		
		retorno = ps.execute();
		ps.close();
		
		return retorno;
	}
	
	public List<CadastroDeExame> listarCadastroDeExames() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from CADASTRO_DE_EXAME");
		List<CadastroDeExame> listaCadastroDeExame = new LinkedList<CadastroDeExame>();
		
		while(rs.next())
		{
			CadastroDeExame cadastroExame = new CadastroDeExame();
			cadastroExame = popularCadastroDeExame(rs);
			listaCadastroDeExame.add(cadastroExame);
		}
		
		return listaCadastroDeExame;
	}

	private CadastroDeExame popularCadastroDeExame(ResultSet rs) throws SQLException, ClassNotFoundException {
		CadastroDeExame cadastroDeExameRetorno = new CadastroDeExame();
		
		cadastroDeExameRetorno.setCAD_EXAME_ID(rs.getInt("CAD_EXAME_ID"));
		cadastroDeExameRetorno.setCAD_EXAME_ABREVIATURA(rs.getString("CAD_EXAME_ABREVIATURA"));
		cadastroDeExameRetorno.setCAD_EXAME_NOME(rs.getString("CAD_EXAME_NOME"));
		
		cadastroDeExameRetorno.setGRUPO_EXAME(grupoExameDAO.buscarGrupoExamePorID(rs.getInt("GRUPO_EXAME_ID")));
		//Setando um OBJETO de GrupoDeExame, por isso � necess�rio fazer a busca no objeto GrupoExameDAO pelo ID que est� no Banco de dados
		
		cadastroDeExameRetorno.setMATERIAL_EXAME(materialExameDAO.buscarMaterialExamePorID(rs.getInt("MATERIAL_EXAME_ID")));
		//Mesmo caso anterior
		
		cadastroDeExameRetorno.setTIPO_LABORATORIO(tipoLaboratorioDAO.buscarTipoLaboratorioPorID(rs.getInt("TIPO_LABORATORIO_ID")));
		//Mesmo caso anterior
		
		cadastroDeExameRetorno.setCAD_EXAME_DIAS_ENTREGA(rs.getString("CAD_EXAME_DIAS_ENTREGA"));
		cadastroDeExameRetorno.setCAD_EXAME_COD_SUS(rs.getString("CAD_EXAME_COD_SUS"));
		cadastroDeExameRetorno.setCAD_EXAME_GRUPO_ETIQUETA(rs.getInt("CAD_EXAME_GRUPO_ETIQUETA"));
		cadastroDeExameRetorno.setCAD_EXAME_TIPO_ENTRADA(rs.getString("EXAME_TIPO_ENTRADA").charAt(0));
		return cadastroDeExameRetorno;
		
		
	}

}
