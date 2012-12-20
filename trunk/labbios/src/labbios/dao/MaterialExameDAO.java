package labbios.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import labbios.db.DatabaseUtil;
import labbios.db.MaterialExame;

public class MaterialExameDAO extends DatabaseUtil{
	
	public MaterialExame buscarMaterialExamePorID(int materialExameID) throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from MATERIAL_EXAME where MATERIAL_EXAME_ID="+materialExameID);
		MaterialExame materialExameRetorno = null;
		if(rs.next())
		{
			materialExameRetorno = popularMaterialExame(rs);
		}
		
		return materialExameRetorno;
	}

	private MaterialExame popularMaterialExame(ResultSet rs) throws SQLException {
		MaterialExame materialExameRetorno = new MaterialExame();
		materialExameRetorno.setMATERIAL_EXAME_ID(rs.getInt("MATERIAL_EXAME_ID"));
		materialExameRetorno.setMATERIAL_EXAME_ABREV(rs.getString("MATERIAL_EXAME_ABREV"));
		materialExameRetorno.setMATERIAL_EXAME_NOME(rs.getString("MATERIAL_EXAME_NOME"));
		return materialExameRetorno;
		
	}
	
	

}
