package labbios.dto;

import java.util.Date;

public class Solicitacao {
	
	private int SOL_ID;
	private Paciente PACIENTE;
	private Date SOL_DT_CRIACAO;
	private Status STATUS;
	private Medico MEDICO;
	private Animal ANIMAL;
	private double SOL_VALOR;
	private String SOL_OBS;
	private double SOL_DESC_PERCENTUAL;
	private double SOL_DESC_DINHEIRO;
	private int SOL_FLAG_URGENTE;
	private double SOL_VALOR_PAGO;
	
	
	public int getSOL_ID() {
		return SOL_ID;
	}
	public void setSOL_ID(int sOL_ID) {
		SOL_ID = sOL_ID;
	}
	public Paciente getPACIENTE() {
		return PACIENTE;
	}
	public void setPACIENTE(Paciente pACIENTE) {
		PACIENTE = pACIENTE;
	}
	public Date getSOL_DT_CRIACAO() {
		return SOL_DT_CRIACAO;
	}
	public void setSOL_DT_CRIACAO(Date sOL_DT_CRIACAO) {
		SOL_DT_CRIACAO = sOL_DT_CRIACAO;
	}
	public Status getSTATUS() {
		return STATUS;
	}
	public void setSTATUS(Status sTATUS) {
		STATUS = sTATUS;
	}
	public Medico getMEDICO() {
		return MEDICO;
	}
	public void setMEDICO(Medico mEDICO) {
		MEDICO = mEDICO;
	}
	public Animal getANIMAL() {
		return ANIMAL;
	}
	public void setANIMAL(Animal aNIMAL) {
		ANIMAL = aNIMAL;
	}
	public double getSOL_VALOR() {
		return SOL_VALOR;
	}
	public void setSOL_VALOR(double sOL_VALOR) {
		SOL_VALOR = sOL_VALOR;
	}
	
	public double getSOL_DESC_PERCENTUAL() {
		return SOL_DESC_PERCENTUAL;
	}
	public void setSOL_DESC_PERCENTUAL(double sOL_DESC_PERCENTUAL) {
		SOL_DESC_PERCENTUAL = sOL_DESC_PERCENTUAL;
	}
	public double getSOL_DESC_DINHEIRO() {
		return SOL_DESC_DINHEIRO;
	}
	public void setSOL_DESC_DINHEIRO(double sOL_DESC_DINHEIRO) {
		SOL_DESC_DINHEIRO = sOL_DESC_DINHEIRO;
	}
	public int getSOL_FLAG_URGENTE() {
		return SOL_FLAG_URGENTE;
	}
	public void setSOL_FLAG_URGENTE(int sOL_FLAG_URGENTE) {
		SOL_FLAG_URGENTE = sOL_FLAG_URGENTE;
	}
	public double getSOL_VALOR_PAGO() {
		return SOL_VALOR_PAGO;
	}
	public void setSOL_VALOR_PAGO(double sOL_VALOR_PAGO) {
		SOL_VALOR_PAGO = sOL_VALOR_PAGO;
	}
	public String getSOL_OBS() {
		return SOL_OBS;
	}
	public void setSOL_OBS(String sOL_OBS) {
		SOL_OBS = sOL_OBS;
	}
	
	
	

}
