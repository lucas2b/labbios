package labbios.beans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import labbios.dao.ResultadoDAO;
import labbios.dto.Exame;
import labbios.dto.Resultado;

public class RelatoriosBean {
	
	private Exame exameSelecionado;
	private List<Resultado> listaSuporte;
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	
	
	public String inicioRelatorios() throws SQLException, ClassNotFoundException, JRException, IOException
	{
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 || resultadoDAO.verificaEntradaExistente(exameSelecionado) == false )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"O Resultado ainda nao existe!", ""));
		}
		else
		{
							listaSuporte = resultadoDAO.recuperarResultado(exameSelecionado);
							
							List<String> cabecalho = resultadoDAO.cabecalhoDeExame(exameSelecionado);
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("nomeDoExame", cabecalho.get(0));
							map.put("nomePaciente", cabecalho.get(1));
							map.put("codigoDePaciente", cabecalho.get(2));
							map.put("dataDeNascimento", cabecalho.get(3));
							map.put("dataDeExame", cabecalho.get(4));
							map.put("medico", cabecalho.get(5));
							map.put("crm", cabecalho.get(6));
							map.put("convenio", cabecalho.get(7));
							map.put("listaSuporte", listaSuporte);
							
							 List<Map<String,?>> maps = new ArrayList<Map<String, ?>> (); 
							 maps.add(map);
							
							JRMapCollectionDataSource parametros = new JRMapCollectionDataSource(maps);
							
							String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/relatorioExame.jasper");
							JasperPrint print = JasperFillManager.fillReport(reportPath, new HashMap(), parametros);
							
							HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
							response.addHeader("Content-disposition", "attachment; filename=Paciente: "+cabecalho.get(1)+" Exame: "+cabecalho.get(0)+".pdf"); //Montando nome do arquivo
							ServletOutputStream servletOutputStream = response.getOutputStream();
							JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
							FacesContext.getCurrentInstance().responseComplete();
					
		}
		return null;

	}


	public Exame getExameSelecionado() {
		return exameSelecionado;
	}


	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}


	public List<Resultado> getListaSuporte() {
		return listaSuporte;
	}


	public void setListaSuporte(List<Resultado> listaSuporte) {
		this.listaSuporte = listaSuporte;
	}




}
