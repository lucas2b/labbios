package labbios.beans;

import java.sql.SQLException;
import java.util.List;

import labbios.dao.CadastroDeExameDAO;
import labbios.dao.ConvenioDAO;
import labbios.dao.TabelaPrecosDAO;
import labbios.dto.TabelaPrecos;

public class TabelaDePrecosBean {
	
	ConvenioDAO convenioDAO = new ConvenioDAO();
	CadastroDeExameDAO cadastroDeExameDAO = new CadastroDeExameDAO();
	TabelaPrecosDAO tabelaDePrecosDAO = new TabelaPrecosDAO();
	
	
	private double valor;
	private int convenioID;
	private int exameCadastrado;
	private char categoriaIPE;
	private TabelaPrecos tabelaDePrecosSelecionado;
	
	
	public String adicionarValorDeExame() throws ClassNotFoundException, SQLException
	{
		TabelaPrecos tabelaPrecos = new TabelaPrecos();
		tabelaPrecos.setTAB_PRECOS_VALOR(valor);
		tabelaPrecos.setTAB_PRECOS_CAT_IPE(categoriaIPE);
		tabelaPrecos.setCONVENIO(convenioDAO.buscarConvenioPorID(convenioID));
		tabelaPrecos.setEXAME(cadastroDeExameDAO.buscarCadastroDeExamePorID(exameCadastrado));
		
		tabelaDePrecosDAO.adicionarValor(tabelaPrecos);
		
		return "refresh";
		
	}
	
	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public int getConvenioID() {
		return convenioID;
	}

	public void setConvenioID(int convenioID) {
		this.convenioID = convenioID;
	}

	public int getExameCadastrado() {
		return exameCadastrado;
	}

	public void setExameCadastrado(int exameCadastrado) {
		this.exameCadastrado = exameCadastrado;
	}

	public char getCategoriaIPE() {
		return categoriaIPE;
	}

	public void setCategoriaIPE(char categoriaIPE) {
		this.categoriaIPE = categoriaIPE;
	}

	public TabelaPrecos getTabelaDePrecosSelecionado() {
		return tabelaDePrecosSelecionado;
	}

	public void setTabelaDePrecosSelecionado(TabelaPrecos tabelaDePrecosSelecionado) {
		this.tabelaDePrecosSelecionado = tabelaDePrecosSelecionado;
	}

	public String editarValorDeExame() throws ClassNotFoundException, SQLException
	{
		tabelaDePrecosDAO.editarValor(tabelaDePrecosSelecionado);
		return "refresh";
	}
	
	public List<TabelaPrecos> getValoresDeExames() throws ClassNotFoundException, SQLException
	{
		return tabelaDePrecosDAO.listarTabelaDePrecos();
	}

}
