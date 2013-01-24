package labbios.beans;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import labbios.dao.DadosDoExameDAO;
import labbios.dao.ExameDAO;
import labbios.dao.ResultadoDAO;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.Exame;
import labbios.dto.Resultado;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


public class EntradaDeResultadosBean {
	
	//Atributos de controle
	private Exame exameSelecionado;
	private boolean flagNovaEntrada = false;
	
	//DAOS usados
	private DadosDoExameDAO dadosDoExameDAO = new DadosDoExameDAO();
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	private ExameDAO exameDAO = new ExameDAO();
	
	
	//Listas de auxílio
	private List<Resultado> listaDeResultado; //Lista de inserção recuperada à partir do molde + entradas do resultado
	private List<Resultado> listaSuporte;
	private List<DadosDoExameSuporte> tabelaMolde; //Molde da tabela de exames recuperado
	
	
	//Função de início
	public String inicioEntradaResultado() throws SQLException, ClassNotFoundException
	{
		
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 )
		{
			//Caso em que o usuário adicionou o exame e tentou entrar resultado sem salvar solicitação antes, retorna para listagem de solicitações
			return retornarParaSolicitacao();
		}
		else
		{
			//Caso tudo OK, prossegue com a rotina de inicialização
		
					if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
					{
							//Update
							flagNovaEntrada = false;
							listaSuporte = resultadoDAO.recuperarResultado(exameSelecionado);
					}
					else
					{
							//Insert
							flagNovaEntrada = true;
							
							tabelaMolde = dadosDoExameDAO.recuperarTabela(exameSelecionado.getCAD_EXAME());
							listaSuporte = new LinkedList<Resultado>();
							
							for(DadosDoExameSuporte molde: tabelaMolde)
							{
								Resultado resultado = new Resultado();
								resultado.setEXAME(exameDAO.buscarExamePorID(exameSelecionado.getEXAME_ID()));
								resultado.setRESULT_PARAMETRO(molde.getParametro());
								resultado.setRESULT_UNIDADE(molde.getUnidade());
								resultado.setRESULT_VALOR_REFERENCIA(molde.getReferencia());
								resultado.setRESULT_VALOR_ENCONTRADO("");
								resultado.setRESULT_OBSERVACOES("");
								listaSuporte.add(resultado);
							}
							
							/*
							 * Descrição do for acima:
							 * Popula uma lista de resultados que será jogada no grid, adiciona os campos vindos do molde de Resultado
							 * como o Parametro, Unidade e o valor de referência e dois valores em brancos para Valor Encontrado
							 * e Observações, para que os espaços em branco no grid possam ser editados corretamente
 							 */
					}
					
					if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().contains("HEMO"))
						return "resultadoTipoHemograma";
					 else
						 return "entradaDeResultadosTipoComum";
		}
		
	}
	
	public String botaoGravarResultados() throws SQLException, ClassNotFoundException
	{
		if(flagNovaEntrada)
			resultadoDAO.inserirNovoResultado(listaSuporte);
		else
			resultadoDAO.updateResultadoExistente(listaSuporte);
		
		return retornarParaSolicitacao();
	}
	
	
	//ATENÇÃO, atributos TEMPORÁRIOS para demonstração de cabeçalho de relatório
	String nomePaciente;
	String nomeDoExame;
	String codigoPaciente;
	String dataDeNasicmento;
	String dataDeRealizacao;
	String medico;
	String crm;
	String convenio;
	
	
	public String extrairRelatorioDeExame() throws NumberFormatException, SQLException, ClassNotFoundException
	{
		List<String> cabecalho = resultadoDAO.cabecalhoDeExame(exameSelecionado);
		setNomeDoExame(cabecalho.get(0));
		setNomePaciente(cabecalho.get(1));
		setCodigoPaciente(cabecalho.get(2));
		setDataDeNasicmento(cabecalho.get(3));
		setDataDeRealizacao(cabecalho.get(4));
		setMedico(cabecalho.get(5));
		setCrm(cabecalho.get(6));
		setConvenio(cabecalho.get(7));
		
		return "visualizarRelatorio";
	}
	
	public String extrairRelatorioDeExame2() throws JRException, NumberFormatException, SQLException, ClassNotFoundException, IOException
	{
		List<String> listaRelatorio = new ArrayList<String>();
		for(Resultado resultado : listaSuporte)
		{
			listaRelatorio.add(resultado.getRESULT_PARAMETRO());
			listaRelatorio.add(resultado.getRESULT_VALOR_ENCONTRADO());
			listaRelatorio.add(resultado.getRESULT_UNIDADE());
			listaRelatorio.add(resultado.getRESULT_VALOR_REFERENCIA());
			listaRelatorio.add(resultado.getRESULT_OBSERVACOES());
		}
		
		
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
		
		JRMapCollectionDataSource simpleDS = new JRMapCollectionDataSource(maps);
		
		String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/relatorios/relatorioExame3.jrxml");
		JasperReport report = JasperCompileManager.compileReport(reportPath);
		JasperPrint print = JasperFillManager.fillReport(report, new HashMap(), simpleDS);
		
		
		HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		response.addHeader("Content-disposition", "attachment; filename=Exame.pdf");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
		FacesContext.getCurrentInstance().responseComplete();
		return "refresh";
	}
	
	
	public String retornarParaSolicitacao()
	{
		return "editarSolicitacao";
	}
	
	//GETTERS AND SETTERS

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}

	public List<Resultado> getListaDeResultado() {
		return listaDeResultado;
	}

	public void setListaDeResultado(List<Resultado> listaDeResultado) {
		this.listaDeResultado = listaDeResultado;
	}

	public List<DadosDoExameSuporte> getTabelaMolde() {
		return tabelaMolde;
	}

	public void setTabelaMolde(List<DadosDoExameSuporte> tabelaMolde) {
		this.tabelaMolde = tabelaMolde;
	}

	public List<Resultado> getListaSuporte() {
		return listaSuporte;
	}

	public void setListaSuporte(List<Resultado> listaSuporte) {
		this.listaSuporte = listaSuporte;
	}

	
	//GETTERS E SETTERS TEMPORARIOS
	
	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public String getCodigoPaciente() {
		return codigoPaciente;
	}

	public void setCodigoPaciente(String codigoPaciente) {
		this.codigoPaciente = codigoPaciente;
	}

	public String getDataDeNasicmento() {
		return dataDeNasicmento;
	}

	public void setDataDeNasicmento(String dataDeNasicmento) {
		this.dataDeNasicmento = dataDeNasicmento;
	}

	public String getDataDeRealizacao() {
		return dataDeRealizacao;
	}

	public void setDataDeRealizacao(String dataDeRealizacao) {
		this.dataDeRealizacao = dataDeRealizacao;
	}

	public String getMedico() {
		return medico;
	}

	public void setMedico(String medico) {
		this.medico = medico;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getNomeDoExame() {
		return nomeDoExame;
	}

	public void setNomeDoExame(String nomeDoExame) {
		this.nomeDoExame = nomeDoExame;
	}

}
