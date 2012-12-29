package labbios.dto;

public class Convenio {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CONVENIO_ID;
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
		Convenio other = (Convenio) obj;
		if (CONVENIO_ID != other.CONVENIO_ID)
			return false;
		return true;
	}
	private int CONVENIO_ID;
	private String CONVENIO_NOME;
	
	public int getCONVENIO_ID() {
		return CONVENIO_ID;
	}
	public void setCONVENIO_ID(int cONVENIO_ID) {
		CONVENIO_ID = cONVENIO_ID;
	}
	public String getCONVENIO_NOME() {
		return CONVENIO_NOME;
	}
	public void setCONVENIO_NOME(String cONVENIO_NOME) {
		CONVENIO_NOME = cONVENIO_NOME;
	}

}
