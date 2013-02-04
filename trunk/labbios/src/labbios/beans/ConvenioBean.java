package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.ConvenioDAO;
import labbios.dto.Convenio;

public class ConvenioBean {
	
	private ConvenioDAO convenioDAO = new ConvenioDAO(); 
	private String nome;
	private Convenio convenio = new Convenio();
	
	public ConvenioBean() {
		inicio();
	}
	public String inicio(){
		convenio = new Convenio();
		convenio.setCONVENIO_NOME("asdfsdafsda");
		return "refresh";
	}
	
	public String adicionarConvenio() throws ClassNotFoundException, SQLException
	{
		convenio = new Convenio();
		convenio.setCONVENIO_NOME(nome);
		convenioDAO.adicionarConvenio(convenio);
		return inicio();
	}
	
	public List<Convenio> getConvenios() throws ClassNotFoundException, SQLException
	{
		return convenioDAO.listarConvenios();
	}
	
	public String atualizar()
	{
		return "refresh";
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
