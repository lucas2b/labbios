package labbios.db;

import java.sql.Date;



public class Paciente {
	
	private int PACIENTE_ID;
	private String PACIENTE_NOME;
	private char PACIENTE_SEXO;
	private String PACIENTE_CPF;
	private String PACIENTE_RG;
	private Date PACIENTE_DT_NASCIMENTO;
	private String PACIENTE_FONE;
	private String PACIENTE_CELULAR;
	private String PACIENTE_ENDERECO;
	private String PACIENTE_BAIRRO;
	private String PACIENTE_CEP;
	private String PACIENTE_EMAIL;
	private String PACIENTE_OBS_MEDICACOES;
	private String PACIENTE_OBS_CONVENIOS;
	private Date PACIENTE_DT_CRIACAO;
	private Cidade CIDADE;
	private String PACIENTE_RESPONSAVEL;
	
	public int getPACIENTE_ID() {
		return PACIENTE_ID;
	}
	public void setPACIENTE_ID(int pACIENTE_ID) {
		PACIENTE_ID = pACIENTE_ID;
	}
	public String getPACIENTE_NOME() {
		return PACIENTE_NOME;
	}
	public void setPACIENTE_NOME(String pACIENTE_NOME) {
		PACIENTE_NOME = pACIENTE_NOME;
	}
	public char getPACIENTE_SEXO() {
		return PACIENTE_SEXO;
	}
	public void setPACIENTE_SEXO(char pACIENTE_SEXO) {
		PACIENTE_SEXO = pACIENTE_SEXO;
	}
	public String getPACIENTE_CPF() {
		return PACIENTE_CPF;
	}
	public void setPACIENTE_CPF(String pACIENTE_CPF) {
		PACIENTE_CPF = pACIENTE_CPF;
	}
	public String getPACIENTE_RG() {
		return PACIENTE_RG;
	}
	public void setPACIENTE_RG(String pACIENTE_RG) {
		PACIENTE_RG = pACIENTE_RG;
	}
	public Date getPACIENTE_DT_NASCIMENTO() {
		return PACIENTE_DT_NASCIMENTO;
	}
	public void setPACIENTE_DT_NASCIMENTO(Date pACIENTE_DT_NASCIMENTO) {
		PACIENTE_DT_NASCIMENTO = pACIENTE_DT_NASCIMENTO;
	}
	public String getPACIENTE_FONE() {
		return PACIENTE_FONE;
	}
	public void setPACIENTE_FONE(String pACIENTE_FONE) {
		PACIENTE_FONE = pACIENTE_FONE;
	}
	public String getPACIENTE_CELULAR() {
		return PACIENTE_CELULAR;
	}
	public void setPACIENTE_CELULAR(String pACIENTE_CELULAR) {
		PACIENTE_CELULAR = pACIENTE_CELULAR;
	}
	public String getPACIENTE_ENDERECO() {
		return PACIENTE_ENDERECO;
	}
	public void setPACIENTE_ENDERECO(String pACIENTE_ENDERECO) {
		PACIENTE_ENDERECO = pACIENTE_ENDERECO;
	}
	public String getPACIENTE_BAIRRO() {
		return PACIENTE_BAIRRO;
	}
	public void setPACIENTE_BAIRRO(String pACIENTE_BAIRRO) {
		PACIENTE_BAIRRO = pACIENTE_BAIRRO;
	}
	public String getPACIENTE_CEP() {
		return PACIENTE_CEP;
	}
	public void setPACIENTE_CEP(String pACIENTE_CEP) {
		PACIENTE_CEP = pACIENTE_CEP;
	}
	public String getPACIENTE_EMAIL() {
		return PACIENTE_EMAIL;
	}
	public void setPACIENTE_EMAIL(String pACIENTE_EMAIL) {
		PACIENTE_EMAIL = pACIENTE_EMAIL;
	}
	public String getPACIENTE_OBS_MEDICACOES() {
		return PACIENTE_OBS_MEDICACOES;
	}
	public void setPACIENTE_OBS_MEDICACOES(String pACIENTE_OBS_MEDICACOES) {
		PACIENTE_OBS_MEDICACOES = pACIENTE_OBS_MEDICACOES;
	}
	public String getPACIENTE_OBS_CONVENIOS() {
		return PACIENTE_OBS_CONVENIOS;
	}
	public void setPACIENTE_OBS_CONVENIOS(String pACIENTE_OBS_CONVENIOS) {
		PACIENTE_OBS_CONVENIOS = pACIENTE_OBS_CONVENIOS;
	}
	public Date getPACIENTE_DT_CRIACAO() {
		return PACIENTE_DT_CRIACAO;
	}
	public void setPACIENTE_DT_CRIACAO(Date pACIENTE_DT_CRIACAO) {
		PACIENTE_DT_CRIACAO = pACIENTE_DT_CRIACAO;
	}
	public Cidade getCIDADE() {
		return CIDADE;
	}
	public void setCIDADE(Cidade cIDADE) {
		CIDADE = cIDADE;
	}
	public String getPACIENTE_RESPONSAVEL() {
		return PACIENTE_RESPONSAVEL;
	}
	public void setPACIENTE_RESPONSAVEL(String pACIENTE_RESPONSAVEL) {
		PACIENTE_RESPONSAVEL = pACIENTE_RESPONSAVEL;
	}
	
	

}
