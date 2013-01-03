package labbios.dto;

import java.sql.Date;



public class Exame {
	
	private int EXAME_ID;
	private Status STATUS;
	private Solicitacao SOLICITACAO;
	private CadastroDeExame CAD_EXAME;
	private Date EXAME_DT_REALIZACAO;
	private TabelaPrecos TAB_PRECOS_ID;
	private char EXAME_CATEGORIA_IP;
	
	public int getEXAME_ID() {
		return EXAME_ID;
	}
	public void setEXAME_ID(int eXAME_ID) {
		EXAME_ID = eXAME_ID;
	}
	public Status getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Status sTATUS) {
		STATUS = sTATUS;
	}
	public Solicitacao getSOLICITACAO() {
		return SOLICITACAO;
	}
	public void setSOLICITACAO(Solicitacao sOLICITACAO) {
		SOLICITACAO = sOLICITACAO;
	}
	public CadastroDeExame getCAD_EXAME() {
		return CAD_EXAME;
	}
	public void setCAD_EXAME(CadastroDeExame cAD_EXAME) {
		CAD_EXAME = cAD_EXAME;
	}
	public Date getEXAME_DT_REALIZACAO() {
		return EXAME_DT_REALIZACAO;
	}
	public void setEXAME_DT_REALIZACAO(Date eXAME_DT_REALIZACAO) {
		EXAME_DT_REALIZACAO = eXAME_DT_REALIZACAO;
	}
	public TabelaPrecos getEXAME_VALOR() {
		return TAB_PRECOS_ID;
	}
	public void setEXAME_VALOR(TabelaPrecos eXAME_VALOR) {
		TAB_PRECOS_ID = eXAME_VALOR;
	}
	public char getEXAME_CATEGORIA_IP() {
		return EXAME_CATEGORIA_IP;
	}
	public void setEXAME_CATEGORIA_IP(char eXAME_CATEGORIA_IP) {
		EXAME_CATEGORIA_IP = eXAME_CATEGORIA_IP;
	}
	

}
