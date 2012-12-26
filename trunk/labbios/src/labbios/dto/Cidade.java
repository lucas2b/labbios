package labbios.dto;

public class Cidade {
	
	private int CIDADE_ID;
	private String CIDADE_NOME;
	private Uf UF;
	
	public int getCIDADE_ID() {
		return CIDADE_ID;
	}
	public void setCIDADE_ID(int cIDADE_ID) {
		CIDADE_ID = cIDADE_ID;
	}
	public String getCIDADE_NOME() {
		return CIDADE_NOME;
	}
	public void setCIDADE_NOME(String cIDADE_NOME) {
		CIDADE_NOME = cIDADE_NOME;
	}
	public Uf getUF() {
		return UF;
	}
	public void setUF(Uf uF) {
		UF = uF;
	}
	
	

}
