package labbios.dto;

public class DadosDoExame {
	
	private String PARAMETRO;
	private String UNIDADE;
	private String REFERENCIA;
	private CadastroDeExame EXAME;
	
	public CadastroDeExame getEXAME() {
		return EXAME;
	}
	public void setEXAME(CadastroDeExame eXAME) {
		EXAME = eXAME;
	}
	public String getPARAMETRO() {
		return PARAMETRO;
	}
	public void setPARAMETRO(String pARAMETRO) {
		PARAMETRO = pARAMETRO;
	}
	public String getUNIDADE() {
		return UNIDADE;
	}
	public void setUNIDADE(String uNIDADE) {
		UNIDADE = uNIDADE;
	}
	public String getREFERENCIA() {
		return REFERENCIA;
	}
	public void setREFERENCIA(String rEFERENCIA) {
		REFERENCIA = rEFERENCIA;
	}

	
	
	

}
