package labbios.db;

public class Resultado {
	
	private int RESULTADO_ID;
	private String RESULTADO_VALOR_ENCONTRADO;
	private String RESULTADO_DESCRICAO;
	private String RESULTADO_VALOR_REFERENCIA;
	private String RESULTADO_UNIDADE;
	private String RESULTADO_OBSERVACOES;
	private Exame EXAME;
	
	
	public int getRESULTADO_ID() {
		return RESULTADO_ID;
	}
	public void setRESULTADO_ID(int rESULTADO_ID) {
		RESULTADO_ID = rESULTADO_ID;
	}
	public String getRESULTADO_VALOR_ENCONTRADO() {
		return RESULTADO_VALOR_ENCONTRADO;
	}
	public void setRESULTADO_VALOR_ENCONTRADO(String rESULTADO_VALOR_ENCONTRADO) {
		RESULTADO_VALOR_ENCONTRADO = rESULTADO_VALOR_ENCONTRADO;
	}
	public String getRESULTADO_DESCRICAO() {
		return RESULTADO_DESCRICAO;
	}
	public void setRESULTADO_DESCRICAO(String rESULTADO_DESCRICAO) {
		RESULTADO_DESCRICAO = rESULTADO_DESCRICAO;
	}
	public String getRESULTADO_VALOR_REFERENCIA() {
		return RESULTADO_VALOR_REFERENCIA;
	}
	public void setRESULTADO_VALOR_REFERENCIA(String rESULTADO_VALOR_REFERENCIA) {
		RESULTADO_VALOR_REFERENCIA = rESULTADO_VALOR_REFERENCIA;
	}
	public String getRESULTADO_UNIDADE() {
		return RESULTADO_UNIDADE;
	}
	public void setRESULTADO_UNIDADE(String rESULTADO_UNIDADE) {
		RESULTADO_UNIDADE = rESULTADO_UNIDADE;
	}
	public String getRESULTADO_OBSERVACOES() {
		return RESULTADO_OBSERVACOES;
	}
	public void setRESULTADO_OBSERVACOES(String rESULTADO_OBSERVACOES) {
		RESULTADO_OBSERVACOES = rESULTADO_OBSERVACOES;
	}
	public Exame getEXAME() {
		return EXAME;
	}
	public void setEXAME(Exame eXAME) {
		EXAME = eXAME;
	}
	
	

}
