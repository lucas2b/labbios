package labbios.dto;

public class GrupoExame {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + GRUPO_EXAME_ID;
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
		GrupoExame other = (GrupoExame) obj;
		if (GRUPO_EXAME_ID != other.GRUPO_EXAME_ID)
			return false;
		return true;
	}
	private int GRUPO_EXAME_ID;
	private String GRUPO_EXAME_ABREV;
	private String GRUPO_EXAME_NOME;
	
	public int getGRUPO_EXAME_ID() {
		return GRUPO_EXAME_ID;
	}
	public void setGRUPO_EXAME_ID(int gRUPO_EXAME_ID) {
		GRUPO_EXAME_ID = gRUPO_EXAME_ID;
	}
	public String getGRUPO_EXAME_ABREV() {
		return GRUPO_EXAME_ABREV;
	}
	public void setGRUPO_EXAME_ABREV(String gRUPO_EXAME_ABREV) {
		GRUPO_EXAME_ABREV = gRUPO_EXAME_ABREV;
	}
	public String getGRUPO_EXAME_NOME() {
		return GRUPO_EXAME_NOME;
	}
	public void setGRUPO_EXAME_NOME(String gRUPO_EXAME_NOME) {
		GRUPO_EXAME_NOME = gRUPO_EXAME_NOME;
	}
	
	

}
