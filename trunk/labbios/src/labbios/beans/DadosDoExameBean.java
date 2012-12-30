package labbios.beans;
import java.sql.SQLException;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import labbios.dao.DadosDoExameDAO;
import labbios.dto.CadastroDeExame;
import labbios.dto.DadosDoExameSuporte;
import javax.servlet.http.HttpServletRequest;


public class DadosDoExameBean {
	
	private boolean flagGravar=false;
	private List<DadosDoExameSuporte> listaSuporte;
	private CadastroDeExame exameSelecionado;
	private DadosDoExameDAO dadosDoExameDAO = new DadosDoExameDAO();
	FacesContext context = FacesContext.getCurrentInstance();
	
	public DadosDoExameBean()
	{
	
	
		//try
//		{
//			boolean flagRetorno = dadosDoExameDAO.verificarExistenciaDeTabela(exameSelecionado.getCAD_EXAME_NOME());
//			System.out.print("Chegou aqui 2");
//			if(flagRetorno)
//			{	
//				
//				listaSuporte = dadosDoExameDAO.recuperarTabela(exameSelecionado.getCAD_EXAME_NOME());
//			}
//			else
//			{
//				flagGravar = true;
//				listaSuporte = new LinkedList<DadosDoExameSuporte>();
//				
//				for(int i=0; i<35; i++)
//				{
//					listaSuporte.add(new DadosDoExameSuporte());			
//				}
//			}
//			
//		}
//		catch(Exception e){}
	}
	
	
	public void mostrarAtributo()
	{
		System.out.println(exameSelecionado.getCAD_EXAME_NOME());
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
			dadosDoExameDAO.adicionarDadosDoExame(listaSuporte, exameSelecionado.getCAD_EXAME_NOME());
		}
		else
		{
			dadosDoExameDAO.atualizarDadosDoExame(listaSuporte, exameSelecionado.getCAD_EXAME_NOME());
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
