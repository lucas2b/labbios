package labbios.dto;

public class MaterialExame {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MATERIAL_EXAME_ID;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialExame other = (MaterialExame) obj;
		if (MATERIAL_EXAME_ID != other.MATERIAL_EXAME_ID)
			return false;
		return true;
	}
	private int MATERIAL_EXAME_ID;
	private String MATERIAL_EXAME_ABREV;
	private String MATERIAL_EXAME_NOME;
	
	public int getMATERIAL_EXAME_ID() {
		return MATERIAL_EXAME_ID;
	}
	public void setMATERIAL_EXAME_ID(int mATERIAL_EXAME_ID) {
		MATERIAL_EXAME_ID = mATERIAL_EXAME_ID;
	}
	public String getMATERIAL_EXAME_ABREV() {
		return MATERIAL_EXAME_ABREV;
	}
	public void setMATERIAL_EXAME_ABREV(String mATERIAL_EXAME_ABREV) {
		MATERIAL_EXAME_ABREV = mATERIAL_EXAME_ABREV;
	}
	public String getMATERIAL_EXAME_NOME() {
		return MATERIAL_EXAME_NOME;
	}
	public void setMATERIAL_EXAME_NOME(String mATERIAL_EXAME_NOME) {
		MATERIAL_EXAME_NOME = mATERIAL_EXAME_NOME;
	}
	
	

}
