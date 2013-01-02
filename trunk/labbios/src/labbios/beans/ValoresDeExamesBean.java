package labbios.beans;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.model.SelectItem;

import labbios.dao.ConvenioDAO;
import labbios.dao.TabelaPrecosDAO;
import labbios.dto.CadastroDeExame;
import labbios.dto.Convenio;
import labbios.dto.TabelaPrecos;

public class ValoresDeExamesBean {
	private ConvenioDAO 	   convenioDAO = new ConvenioDAO();
	private TabelaPrecosDAO    tabelaDePrecosDAO = new TabelaPrecosDAO();
	
	private double 			valorDoExame;
	private Convenio 		convenio;
	private TabelaPrecos 	tabelaDePrecosSelecionada;
	private CadastroDeExame cadastroDeExameSelecionado;
		
	
	public List<SelectItem> getConvenios() throws ClassNotFoundException, SQLException
	{
		List<SelectItem> toReturn = new LinkedList<SelectItem>();
		for(Convenio convenio : convenioDAO.listarConvenios())
		{
			toReturn.add(new SelectItem(convenio, convenio.getCONVENIO_NOME()));
			//Passa para a lista de SelectItem o objeto e o atributo nome do produto
		}
		return toReturn;	
	}
	
	public String adicionarValorDeExame() throws ClassNotFoundException, SQLException
	{
		TabelaPrecos tabelaPrecos = new TabelaPrecos();	
		tabelaPrecos.setTAB_PRECOS_VALOR(valorDoExame);
		tabelaPrecos.setCONVENIO(convenio);
		tabelaPrecos.setEXAME(cadastroDeExameSelecionado);
		
		tabelaDePrecosDAO.adicionarValor(tabelaPrecos);
		
		return "refresh";
		
	}
	
	public List<TabelaPrecos> getValoresDeExameSelecionado() throws ClassNotFoundException, SQLException
	{
		return tabelaDePrecosDAO.listarTabelaDePrecosPorExame(cadastroDeExameSelecionado);
	}
	
	public String startEditarValorDeExame()
	{
		return "editarValorDeExame";
	}
	
	
	public String finishEditarValorDeExame() throws ClassNotFoundException, SQLException
	{
		tabelaDePrecosDAO.editarValor(tabelaDePrecosSelecionada);
		return "listarValoresDeExames";
	}

	
	//GETTERS AND SETTERS
	
	public double getValorDoExame() {
		return valorDoExame;
	}

	public void setValorDoExame(double valorDoExame) {
		this.valorDoExame = valorDoExame;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public TabelaPrecos getTabelaDePrecosSelecionada() {
		return tabelaDePrecosSelecionada;
	}

	public void setTabelaDePrecosSelecionada(TabelaPrecos tabelaDePrecosSelecionada) {
		this.tabelaDePrecosSelecionada = tabelaDePrecosSelecionada;
	}

	public CadastroDeExame getCadastroDeExameSelecionado() {
		return cadastroDeExameSelecionado;
	}

	public void setCadastroDeExameSelecionado(
			CadastroDeExame cadastroDeExameSelecionado) {
		this.cadastroDeExameSelecionado = cadastroDeExameSelecionado;
	}

}
