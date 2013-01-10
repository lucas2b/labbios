package labbios.beans;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.CidadeDAO;
import labbios.dao.PacienteDAO;
import labbios.dto.Paciente;

public class PacienteBean {
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private CidadeDAO cidadesDAO = new CidadeDAO();
	private boolean flagNovoPaciente = false;
	private Paciente pacienteSelecionado;
	private Date dataNascimento;

	//Função que dá início ao tratamento de pacientes
	public String manutencaoDePacientes()
	{
		if(pacienteSelecionado == null)
		{
			flagNovoPaciente = true;
			pacienteSelecionado = new Paciente();
			
		}
		return "manutencaoDePacientes";
	}
	
	public String voltarListagem()
	{
		pacienteSelecionado = null;
		flagNovoPaciente = false;
		return "listarPacientes";
	}
	
	public String botaoGravar() throws ClassNotFoundException, SQLException
	{
		if(flagNovoPaciente)
		{
			System.out.println("Novo paciente");
			pacienteDAO.adicionarPaciente(pacienteSelecionado);
		}
		else
		{
			System.out.println("Update de paciente");
			pacienteDAO.editarPaciente(pacienteSelecionado);
		}
		
		return voltarListagem();
	}
	
	public List<Paciente> getPacientes() throws ClassNotFoundException, SQLException
	{
		return pacienteDAO.listarPacientes();
	}
	
	public List<SelectItem> getComboCidades() throws ClassNotFoundException, SQLException 
	{		
		return cidadesDAO.listarCidadesCombo();
	}
	
	//GETTERS AND SETTERS

	public Paciente getPacienteSelecionado() {
		return pacienteSelecionado;
	}

	public void setPacienteSelecionado(Paciente pacienteSelecionado) {
		this.pacienteSelecionado = pacienteSelecionado;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
