package labbios.dto;

public class Resultado {
	
	private int RESULT_ID;
	private double RESULT_VALOR_ENCONTRADO;
	private String RESULT_PARAMETRO;
	private String RESULT_VALOR_REFERENCIA;
	private String RESULT_UNIDADE;
	private String RESULT_OBSERVACOES;
	private Exame EXAME;
	
	public int getRESULT_ID() {
		return RESULT_ID;
	}
	public void setRESULT_ID(int rESULT_ID) {
		RESULT_ID = rESULT_ID;
	}
	
	
	public double getRESULT_VALOR_ENCONTRADO() {
		return RESULT_VALOR_ENCONTRADO;
	}
	public void setRESULT_VALOR_ENCONTRADO(double rESULT_VALOR_ENCONTRADO) {
		RESULT_VALOR_ENCONTRADO = rESULT_VALOR_ENCONTRADO;
	}
	public String getRESULT_PARAMETRO() {
		return RESULT_PARAMETRO;
	}
	public void setRESULT_PARAMETRO(String rESULT_PARAMETRO) {
		RESULT_PARAMETRO = rESULT_PARAMETRO;
	}
	public String getRESULT_VALOR_REFERENCIA() {
		return RESULT_VALOR_REFERENCIA;
	}
	public void setRESULT_VALOR_REFERENCIA(String rESULT_VALOR_REFERENCIA) {
		RESULT_VALOR_REFERENCIA = rESULT_VALOR_REFERENCIA;
	}
	public String getRESULT_UNIDADE() {
		return RESULT_UNIDADE;
	}
	public void setRESULT_UNIDADE(String rESULT_UNIDADE) {
		RESULT_UNIDADE = rESULT_UNIDADE;
	}
	public String getRESULT_OBSERVACOES() {
		return RESULT_OBSERVACOES;
	}
	public void setRESULT_OBSERVACOES(String rESULT_OBSERVACOES) {
		RESULT_OBSERVACOES = rESULT_OBSERVACOES;
	}
	public Exame getEXAME() {
		return EXAME;
	}
	public void setEXAME(Exame eXAME) {
		EXAME = eXAME;
	}
	

}
