package labbios.dto;

public class TipoLaboratorio {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + TIPO_LABORATORIO_ID;
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
		TipoLaboratorio other = (TipoLaboratorio) obj;
		if (TIPO_LABORATORIO_ID != other.TIPO_LABORATORIO_ID)
			return false;
		return true;
	}
	private int TIPO_LABORATORIO_ID;
	private String TIPO_LABORATORIO_NOME;
	
	
	public int getTIPO_LABORATORIO_ID() {
		return TIPO_LABORATORIO_ID;
	}
	public void setTIPO_LABORATORIO_ID(int tIPO_LABORATORIO_ID) {
		TIPO_LABORATORIO_ID = tIPO_LABORATORIO_ID;
	}
	public String getTIPO_LABORATORIO_NOME() {
		return TIPO_LABORATORIO_NOME;
	}
	public void setTIPO_LABORATORIO_NOME(String tIPO_LABORATORIO_NOME) {
		TIPO_LABORATORIO_NOME = tIPO_LABORATORIO_NOME;
	}
	
	

}
