package labbios.beans;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import labbios.dao.ResultadoDAO;
import labbios.dto.Exame;
import labbios.dto.Resultado;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

public class RelatoriosBean {
	
	private boolean tipoHemograma;
	private Exame exameSelecionado;
	private List<Resultado> listaDeResultados;
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	
	
	public String inicioRelatorios() throws SQLException, ClassNotFoundException, JRException, IOException
	{
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 || resultadoDAO.verificaEntradaExistente(exameSelecionado) == false )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"O Resultado ainda nao existe!", ""));
		}
		else
		{
							if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().equals("HEMOGRAMA"))
								tipoHemograma = true;
							else
								tipoHemograma = false;
								
			
							listaDeResultados = resultadoDAO.recuperarResultado(exameSelecionado);
							
							if(tipoHemograma)
								popularParametrosHemograma();
							
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
							map.put("listaSuporte", listaDeResultados);
							
							 List<Map<String,?>> maps = new ArrayList<Map<String, ?>> (); 
							 maps.add(map);
							
							JRMapCollectionDataSource parametros = new JRMapCollectionDataSource(maps);
							
							String reportPath;
							
							if(tipoHemograma)
								reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/relatorioExameHemograma.jasper");
							else
								reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/relatorioExame.jasper");
							
							JasperPrint print = JasperFillManager.fillReport(reportPath, new HashMap(), parametros);
							
							HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
							response.addHeader("Content-disposition", "attachment; filename=Paciente: "+cabecalho.get(1)+" Exame: "+cabecalho.get(0)+".pdf"); //Montando nome do arquivo
							ServletOutputStream servletOutputStream = response.getOutputStream();
							JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
							listaDeResultados = null;
							FacesContext.getCurrentInstance().responseComplete();
					
		}
		return null;

	}
	
	public void popularParametrosHemograma()
	{
		double leucocitosAbsoluto	 = listaDeResultados.get(0).getRESULT_VALOR_ENCONTRADO();
		
		double bastonetesPercentual  = listaDeResultados.get(1).getRESULT_VALOR_ENCONTRADO();
		double bastonetesAbsoluto	 = (bastonetesPercentual/100)*leucocitosAbsoluto;
		
		double segmentadosPercentual = listaDeResultados.get(2).getRESULT_VALOR_ENCONTRADO();
		double segmentadosAbsoluto	 = (segmentadosPercentual/100)*leucocitosAbsoluto;
		
		double eosinofilosPercentual = listaDeResultados.get(3).getRESULT_VALOR_ENCONTRADO();
		double eosinofilosAbsoluto	 = (eosinofilosPercentual/100)*leucocitosAbsoluto;
		
		double monocitosPercentual   = listaDeResultados.get(4).getRESULT_VALOR_ENCONTRADO();
		double monocitosAbsoluto	 = (monocitosPercentual/100)*leucocitosAbsoluto;
		
		double linfocitosPercentual = listaDeResultados.get(5).getRESULT_VALOR_ENCONTRADO();
		double linfocitosAbsoluto	 = (linfocitosPercentual/100)*leucocitosAbsoluto;
		
		listaDeResultados.get(1).setPARAMETRO_HEMOGRAMA_ABSOLUTO(bastonetesAbsoluto);
		listaDeResultados.get(2).setPARAMETRO_HEMOGRAMA_ABSOLUTO(segmentadosAbsoluto);
		listaDeResultados.get(3).setPARAMETRO_HEMOGRAMA_ABSOLUTO(eosinofilosAbsoluto);
		listaDeResultados.get(4).setPARAMETRO_HEMOGRAMA_ABSOLUTO(monocitosAbsoluto);
		listaDeResultados.get(5).setPARAMETRO_HEMOGRAMA_ABSOLUTO(linfocitosAbsoluto);
		
	}


	public Exame getExameSelecionado() {
		return exameSelecionado;
	}


	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}


	public List<Resultado> getListaSuporte() {
		return listaDeResultados;
	}


	public void setListaSuporte(List<Resultado> listaSuporte) {
		this.listaDeResultados = listaSuporte;
	}




}
