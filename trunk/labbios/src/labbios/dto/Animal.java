package labbios.dto;

import java.sql.Date;



public class Animal {
	private int ANIMAL_ID;
	private String ANIMAL_ESPECIE;
	private Date ANIMAL_DT_NASCIMENTO;
	private String ANIMAL_PROPRIETARIO;
	private String ANIMAL_FONE;
	private String ANIMAL_NOME;
	
	public int getANIMAL_ID() {
		return ANIMAL_ID;
	}
	public void setANIMAL_ID(int aNIMAL_ID) {
		ANIMAL_ID = aNIMAL_ID;
	}
	public String getANIMAL_ESPECIE() {
		return ANIMAL_ESPECIE;
	}
	public void setANIMAL_ESPECIE(String aNIMAL_ESPECIE) {
		ANIMAL_ESPECIE = aNIMAL_ESPECIE;
	}
	public Date getANIMAL_DT_NASCIMENTO() {
		return ANIMAL_DT_NASCIMENTO;
	}
	public void setANIMAL_DT_NASCIMENTO(Date aNIMAL_DT_NASCIMENTO) {
		ANIMAL_DT_NASCIMENTO = aNIMAL_DT_NASCIMENTO;
	}
	public String getANIMAL_PROPRIETARIO() {
		return ANIMAL_PROPRIETARIO;
	}
	public void setANIMAL_PROPRIETARIO(String aNIMAL_PROPRIETARIO) {
		ANIMAL_PROPRIETARIO = aNIMAL_PROPRIETARIO;
	}
	public String getANIMAL_FONE() {
		return ANIMAL_FONE;
	}
	public void setANIMAL_FONE(String aNIMAL_FONE) {
		ANIMAL_FONE = aNIMAL_FONE;
	}
	public String getANIMAL_NOME() {
		return ANIMAL_NOME;
	}
	public void setANIMAL_NOME(String aNIMAL_NOME) {
		ANIMAL_NOME = aNIMAL_NOME;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ANIMAL_ID;
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
		Animal other = (Animal) obj;
		if (ANIMAL_ID != other.ANIMAL_ID)
			return false;
		return true;
	}
	
	

}
