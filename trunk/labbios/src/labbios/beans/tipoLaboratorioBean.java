package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.TipoLaboratorioDAO;
import labbios.db.TipoLaboratorio;

public class tipoLaboratorioBean {
	
	TipoLaboratorioDAO tipoLaboratorioDAO = new TipoLaboratorioDAO();
	private String nome;
	private TipoLaboratorio tipoLaboratorioSelecionado;
	
	public String addTipoDeLaboratorio() throws ClassNotFoundException, SQLException
	{
		TipoLaboratorio tipoLaboratorio = new TipoLaboratorio();
		tipoLaboratorio.setTIPO_LABORATORIO_NOME(nome);
		tipoLaboratorioDAO.adicionarTipoLaboratorio(tipoLaboratorio);
		return "refresh";
	}
	
	public List<TipoLaboratorio> getTiposDeLaboratorio() throws ClassNotFoundException, SQLException
	{
		return tipoLaboratorioDAO.listarTipoLaboratorio();
	}
	
	public String startEditarTipoLaboratorio()
	{
		return "editarTipoLaboratorio";
	}
	
	public String finishEditarTipoLaboratorio() throws ClassNotFoundException, SQLException
	{
		tipoLaboratorioDAO.editarTipoLaboratorio(tipoLaboratorioSelecionado);
		return "listarTiposDeLaboratorio";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoLaboratorio getTipoLaboratorioSelecionado() {
		return tipoLaboratorioSelecionado;
	}

	public void setTipoLaboratorioSelecionado(
			TipoLaboratorio tipoLaboratorioSelecionado) {
		this.tipoLaboratorioSelecionado = tipoLaboratorioSelecionado;
	}

}
