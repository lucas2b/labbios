package labbios.dto;

public class Status {
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + STATUS_ID;
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
		Status other = (Status) obj;
		if (STATUS_ID != other.STATUS_ID)
			return false;
		return true;
	}
	private int STATUS_ID;
	private String STATUS_NOME;
	
	
	public int getSTATUS_ID() {
		return STATUS_ID;
	}
	public void setSTATUS_ID(int sTATUS_ID) {
		STATUS_ID = sTATUS_ID;
	}
	public String getSTATUS_NOME() {
		return STATUS_NOME;
	}
	public void setSTATUS_NOME(String sTATUS_NOME) {
		STATUS_NOME = sTATUS_NOME;
	}
	
	
}
