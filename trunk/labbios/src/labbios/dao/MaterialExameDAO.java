package labbios.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import labbios.db.DatabaseUtil;
import labbios.dto.MaterialExame;

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
	
	public boolean adicionarMaterialExame(MaterialExame materialExame) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Insert into MATERIAL_EXAME set MATERIAL_EXAME_ABREV=?");
		ps.setString(1, materialExame.getMATERIAL_EXAME_NOME());
		retorno = ps.execute();
		return retorno;
	}
	
	public boolean editarMaterialExame(MaterialExame materialExame) throws ClassNotFoundException, SQLException
	{
		boolean retorno = false;
		PreparedStatement ps = getPreparedStatement("Update MATERIAL_EXAME set MATERIAL_EXAME_NOME=? where MATERIAL_EXAME_ID=?");
		ps.setString(1, materialExame.getMATERIAL_EXAME_NOME());
		ps.setInt(2, materialExame.getMATERIAL_EXAME_ID());
		retorno = ps.execute();
		return retorno;
	}
	
	public List<MaterialExame> listarMaterialExame() throws ClassNotFoundException, SQLException
	{
		ResultSet rs = getStatement().executeQuery("Select * from MATERIAL_EXAME");
		List<MaterialExame> listaMaterialExame = new LinkedList<MaterialExame>();
		
		while(rs.next())
		{
			MaterialExame materialExame = new MaterialExame();
			materialExame = popularMaterialExame(rs);
			listaMaterialExame.add(materialExame);
		}
		
		return listaMaterialExame;
		
	}

	private MaterialExame popularMaterialExame(ResultSet rs) throws SQLException {
		MaterialExame materialExameRetorno = new MaterialExame();
		materialExameRetorno.setMATERIAL_EXAME_ID(rs.getInt("MATERIAL_EXAME_ID"));
		materialExameRetorno.setMATERIAL_EXAME_NOME(rs.getString("MATERIAL_EXAME_NOME"));
		return materialExameRetorno;
		
	}
	
	

}
