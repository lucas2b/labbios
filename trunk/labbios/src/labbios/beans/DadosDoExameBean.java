package labbios.beans;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import labbios.dao.DadosDoExameDAO;
import labbios.dto.CadastroDeExame;
import labbios.dto.DadosDoExameSuporte;



public class DadosDoExameBean {
	
	private boolean flagGravar=false;
	private List<DadosDoExameSuporte> listaSuporte;
	private CadastroDeExame exameSelecionado;
	private DadosDoExameDAO dadosDoExameDAO = new DadosDoExameDAO();
	String nomeDoExame;
	
	public DadosDoExameBean()
	{
		
		try
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();    
		    HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);    
		    nomeDoExame = (String) session.getAttribute("exameNome");
		       
		    boolean flagRetorno = dadosDoExameDAO.verificarExistenciaDeTabela(nomeDoExame);
				if(flagRetorno)
				{	
					//Se existe a listagem deste exame
					System.out.println("Existe Listagem");
					listaSuporte = dadosDoExameDAO.recuperarTabela(nomeDoExame);
				}
				else
				{
					//Caso não exista, apresenta 35 linhas em branco
					System.out.println("Nao Existe Listagem");
					flagGravar = true;
					listaSuporte = new LinkedList<DadosDoExameSuporte>();
					
					for(int i=0; i<35; i++)
					{
						listaSuporte.add(new DadosDoExameSuporte());			
					}
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		

	}
	

	public List<DadosDoExameSuporte> getListaSuporte() {
		return listaSuporte;
	}

	public void setListaSuporte(List<DadosDoExameSuporte> listaSuporte) {
		this.listaSuporte = listaSuporte;
	}
	
	public String gravarDadosDoExame() throws ClassNotFoundException, SQLException
	{
		if(flagGravar== true)
		{
			//nova tabela
			dadosDoExameDAO.adicionarDadosDoExame(listaSuporte, nomeDoExame);
		}
		else
		{
			dadosDoExameDAO.atualizarDadosDoExame(listaSuporte, nomeDoExame);
			//update
		}
		
		return "refresh";
	}

	public CadastroDeExame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(CadastroDeExame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}

}
