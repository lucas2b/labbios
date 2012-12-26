package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.ConvenioDAO;
import labbios.dto.Convenio;

public class convenioBean {
	
	private ConvenioDAO convenioDAO = new ConvenioDAO(); 
	private String nome;
	
	public String adicionarConvenio() throws ClassNotFoundException, SQLException
	{
		Convenio convenio = new Convenio();
		convenio.setCONVENIO_NOME(nome);
		convenioDAO.adicionarConvenio(convenio);
		return "refresh";
	}
	
	public List<Convenio> getConvenios() throws ClassNotFoundException, SQLException
	{
		return convenioDAO.listarConvenios();
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
