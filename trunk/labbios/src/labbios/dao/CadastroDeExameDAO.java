package labbios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

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

	private CadastroDeExame popularCadastroDeExame(ResultSet rs) throws SQLException, ClassNotFoundException {
		CadastroDeExame cadastroDeExameRetorno = new CadastroDeExame();
		cadastroDeExameRetorno.setCAD_EXAME_ID(rs.getInt("CAD_EXAME_ID"));
		cadastroDeExameRetorno.setCAD_EXAME_ABREVIATURA(rs.getString("CAD_EXAME_ABREVIATURA"));
		cadastroDeExameRetorno.setCAD_EXAME_NOME(rs.getString("CAD_EXAME_NOME"));
		cadastroDeExameRetorno.setGRUPO_EXAME(grupoExameDAO.buscarGrupoExamePorID(rs.getInt("GRUPO_EXAME_ID")));
		cadastroDeExameRetorno.setMATERIAL_EXAME(materialExameDAO.buscarMaterialExamePorID(rs.getInt("MATERIAL_EXAME_ID")));
		cadastroDeExameRetorno.setTIPO_LABORATORIO(tipoLaboratorioDAO.buscarTipoLaboratorioPorID(rs.getInt("TIPO_LABORATORIO_ID")));
		cadastroDeExameRetorno.setCAD_EXAME_DIAS_ENTREGA(rs.getString("CAD_EXAME_DIAS_ENTREGA"));
		cadastroDeExameRetorno.setCAD_EXAME_COD_SUS(rs.getString("CAD_EXAME_COD_SUS"));
		cadastroDeExameRetorno.setCAD_EXAME_GRUPO_ETIQUETA(rs.getInt("CAD_EXAME_GRUPO_ETIQUETA"));
		cadastroDeExameRetorno.setCAD_EXAME_TIPO_ENTRADA(rs.getString("EXAME_TIPO_ENTRADA").charAt(0));
		return cadastroDeExameRetorno;
		
		
	}

}
