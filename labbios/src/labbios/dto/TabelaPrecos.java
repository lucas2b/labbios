package labbios.dto;

public class TabelaPrecos {
	
	private int TAB_PRECOS_ID;
	private double TAB_PRECOS_VALOR;
	private Convenio CONVENIO;
	private CadastroDeExame EXAME;
	
	
	public int getTAB_PRECOS_ID() {
		return TAB_PRECOS_ID;
	}
	public void setTAB_PRECOS_ID(int tAB_PRECOS_ID) {
		TAB_PRECOS_ID = tAB_PRECOS_ID;
	}
	public double getTAB_PRECOS_VALOR() {
		return TAB_PRECOS_VALOR;
	}
	public void setTAB_PRECOS_VALOR(double tAB_PRECOS_VALOR) {
		TAB_PRECOS_VALOR = tAB_PRECOS_VALOR;
	}
	public Convenio getCONVENIO() {
		return CONVENIO;
	}
	public void setCONVENIO(Convenio cONVENIO) {
		CONVENIO = cONVENIO;
	}
	public CadastroDeExame getEXAME() {
		return EXAME;
	}
	public void setEXAME(CadastroDeExame eXAME) {
		EXAME = eXAME;
	}
	
	

}
