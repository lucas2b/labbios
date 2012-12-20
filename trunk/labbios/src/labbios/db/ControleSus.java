package labbios.db;

import java.util.Date;

public class ControleSus {
	
	private int CONT_SUS_ID;
	private double CONT_SUS_VALOR_ACUM;
	private Exame EXAME;
	private double CONT_SUS_VALOR_EXAME;
	private Date CONT_SUS_DT_REALIZACAO;
	private int CONT_SUS_FLAG_RELATORIO;
	private String CONT_SUS_CODIGO_SUS;
	
	public int getCONT_SUS_ID() {
		return CONT_SUS_ID;
	}
	public void setCONT_SUS_ID(int cONT_SUS_ID) {
		CONT_SUS_ID = cONT_SUS_ID;
	}
	public double getCONT_SUS_VALOR_ACUM() {
		return CONT_SUS_VALOR_ACUM;
	}
	public void setCONT_SUS_VALOR_ACUM(double cONT_SUS_VALOR_ACUM) {
		CONT_SUS_VALOR_ACUM = cONT_SUS_VALOR_ACUM;
	}
	public Exame getEXAME() {
		return EXAME;
	}
	public void setEXAME(Exame eXAME) {
		EXAME = eXAME;
	}
	public double getCONT_SUS_VALOR_EXAME() {
		return CONT_SUS_VALOR_EXAME;
	}
	public void setCONT_SUS_VALOR_EXAME(double cONT_SUS_VALOR_EXAME) {
		CONT_SUS_VALOR_EXAME = cONT_SUS_VALOR_EXAME;
	}
	public Date getCONT_SUS_DT_REALIZACAO() {
		return CONT_SUS_DT_REALIZACAO;
	}
	public void setCONT_SUS_DT_REALIZACAO(Date cONT_SUS_DT_REALIZACAO) {
		CONT_SUS_DT_REALIZACAO = cONT_SUS_DT_REALIZACAO;
	}
	public int getCONT_SUS_FLAG_RELATORIO() {
		return CONT_SUS_FLAG_RELATORIO;
	}
	public void setCONT_SUS_FLAG_RELATORIO(int cONT_SUS_FLAG_RELATORIO) {
		CONT_SUS_FLAG_RELATORIO = cONT_SUS_FLAG_RELATORIO;
	}
	public String getCONT_SUS_CODIGO_SUS() {
		return CONT_SUS_CODIGO_SUS;
	}
	public void setCONT_SUS_CODIGO_SUS(String cONT_SUS_CODIGO_SUS) {
		CONT_SUS_CODIGO_SUS = cONT_SUS_CODIGO_SUS;
	}
	
	
	
	

}
