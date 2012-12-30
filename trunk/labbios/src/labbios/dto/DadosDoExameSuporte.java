package labbios.dto;

public class DadosDoExameSuporte {
	
	private CadastroDeExame exame;
	private String parametro;
	private String unidade;
	private int referencia;
	
	public DadosDoExameSuporte(CadastroDeExame exame, String parametro,
			String unidade, int referencia) {
		super();
		this.exame = exame;
		this.parametro = parametro;
		this.unidade = unidade;
		this.referencia = referencia;
	}
	public CadastroDeExame getExame() {
		return exame;
	}
	public void setExame(CadastroDeExame exame) {
		this.exame = exame;
	}
	public String getParametro() {
		return parametro;
	}
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	public String getUnidade() {
		return unidade;
	}
	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}
	public int getReferencia() {
		return referencia;
	}
	public void setReferencia(int referencia) {
		this.referencia = referencia;
	}
	
	

}
