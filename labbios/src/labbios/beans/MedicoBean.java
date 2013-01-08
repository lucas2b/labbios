package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.CidadeDAO;
import labbios.dao.MedicoDAO;
import labbios.dto.Medico;

public class MedicoBean {
	
	private MedicoDAO medicoDAO = new MedicoDAO();
	private CidadeDAO cidadeDAO = new CidadeDAO();
	
	private Medico medicoSelecionado;
	private boolean flagNovoMedico = false;
	
	public String manutencaoDeMedicos()
	{
		if(medicoSelecionado == null)
		{
			flagNovoMedico = true;
			medicoSelecionado = new Medico();
		}
		
		return "manutencaoDeMedicos";
	}
	
	
	public void adicionarMedico() throws ClassNotFoundException, SQLException
	{
		medicoDAO.adicionarMedico(medicoSelecionado);
		medicoSelecionado = null;
		flagNovoMedico = false;
	}
	
	public void editarMedico() throws ClassNotFoundException, SQLException
	{
		medicoDAO.editarMedico(medicoSelecionado);
		medicoSelecionado = null;
		flagNovoMedico = false;
	}
	
	public String botaoGravar() throws ClassNotFoundException, SQLException
	{
		if(flagNovoMedico == true)
			adicionarMedico();
		else
			editarMedico();
		
		return "listarMedicos";
	}
	
	public String voltarParaListagem()
	{
		medicoSelecionado = null;
		flagNovoMedico = false;
		return "listarMedicos";
	}
	
	public List<Medico> getMedicos() throws ClassNotFoundException, SQLException
	{
		return medicoDAO.listarMedicos();
	}
	
	public List<SelectItem> getComboCidades() throws ClassNotFoundException, SQLException
	{
		return cidadeDAO.listarCidadesCombo();
	}


	public Medico getMedicoSelecionado() {
		return medicoSelecionado;
	}


	public void setMedicoSelecionado(Medico medicoSelecionado) {
		this.medicoSelecionado = medicoSelecionado;
	}

}
