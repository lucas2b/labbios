package labbios.beans;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.CidadeDAO;
import labbios.dao.PacienteDAO;
import labbios.dto.Paciente;

public class PacienteBean {
	PacienteDAO pacienteDAO = new PacienteDAO();
	CidadeDAO cidadesDAO = new CidadeDAO();
	Paciente pacienteSelecionado;
	private Date dataNascimento;

	//Função que dá início ao tratamento de pacientes
	public String manutencaoDePacientes()
	{
		if(pacienteSelecionado == null)
			pacienteSelecionado = new Paciente();
		
		return "manutencaoDePacientes";
	}
	
	public String voltarListagem()
	{
		pacienteSelecionado = null;;
		return "listarPacientes";
	}
	
	public String adicionarPaciente() throws ClassNotFoundException, SQLException
	{

		pacienteSelecionado.setPACIENTE_DT_NASCIMENTO(new java.sql.Date(dataNascimento.getTime()));
		pacienteDAO.adicionarPaciente(pacienteSelecionado);
		return "refresh";
	}
	
	public String editarPaciente() throws ClassNotFoundException, SQLException
	{
		pacienteDAO.editarPaciente(pacienteSelecionado);
		return "refresh";
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
