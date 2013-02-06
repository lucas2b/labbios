package labbios.dto;

public class DadosDeExameTexto {
	
	private int DADOS_TEXTO_ID;
	private String DADOS_TEXTO_TEXTO;
	private CadastroDeExame CAD_EXAME_ID;
	public int getDADOS_TEXTO_ID() {
		return DADOS_TEXTO_ID;
	}
	public void setDADOS_TEXTO_ID(int dADOS_TEXTO_ID) {
		DADOS_TEXTO_ID = dADOS_TEXTO_ID;
	}
	public String getDADOS_TEXTO_TEXTO() {
		return DADOS_TEXTO_TEXTO;
	}
	public void setDADOS_TEXTO_TEXTO(String dADOS_TEXTO_TEXTO) {
		DADOS_TEXTO_TEXTO = dADOS_TEXTO_TEXTO;
	}
	public CadastroDeExame getCAD_EXAME_ID() {
		return CAD_EXAME_ID;
	}
	public void setCAD_EXAME_ID(CadastroDeExame cAD_EXAME_ID) {
		CAD_EXAME_ID = cAD_EXAME_ID;
	}
	
	

}
