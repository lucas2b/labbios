package labbios.beans;


import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import labbios.dao.DadosDoExameDAO;
import labbios.dao.ExameDAO;
import labbios.dao.ResultadoDAO;
import labbios.dto.DadosDoExameSuporte;
import labbios.dto.Exame;
import labbios.dto.Resultado;

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
	private List<Resultado> listaDeExibicao;
	private List<DadosDoExameSuporte> tabelaMolde; //Molde da tabela de exames recuperado
	
	
	//Função de início
	public String inicioEntradaResultado() throws SQLException, ClassNotFoundException
	{
		
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"A solicitação ainda não foi salva! Clique em salvar e retorne para entrar resultados!", ""));
			return null;
		}
		
		
		if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
		{
				//Update
				flagNovaEntrada = false;
				listaDeExibicao = resultadoDAO.recuperarResultado(exameSelecionado);
		}
		else
		{
				//Insert
				flagNovaEntrada = true;
				
				tabelaMolde = dadosDoExameDAO.recuperarTabela(exameSelecionado.getCAD_EXAME());
				listaDeExibicao = new LinkedList<Resultado>();
				
				for(DadosDoExameSuporte molde: tabelaMolde)
				{
					Resultado resultado = new Resultado();
					resultado.setEXAME(exameDAO.buscarExamePorID(exameSelecionado.getEXAME_ID()));
					resultado.setRESULT_PARAMETRO(molde.getParametro());
					resultado.setRESULT_UNIDADE(molde.getUnidade());
					resultado.setRESULT_VALOR_REFERENCIA(molde.getReferencia());
					resultado.setRESULT_VALOR_ENCONTRADO("");
					resultado.setRESULT_OBSERVACOES("");
					listaDeExibicao.add(resultado);
				}
				
		}
						
		
		if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().contains("HEMO"))
		{
			if(!flagNovaEntrada)
				rotinaDeRecuperacaoDeHemograma();
			
			return "resultadoTipoHemograma";
		}
		 else
			 return "entradaDeResultadosTipoComum";
		
	}
	
	public String botaoGravarResultados() throws SQLException, ClassNotFoundException
	{
		if(flagNovaEntrada)
			resultadoDAO.inserirNovoResultado(listaDeExibicao);
		else
			resultadoDAO.updateResultadoExistente(listaDeExibicao);
		
		return retornarParaSolicitacao();
	}
	
	public String retornarParaSolicitacao()
	{
		return "editarSolicitacao";
	}

	
	//AREA DE HEMOGRAMA
	
	//CAMPOS DO LEUCOGRAMA
	
	private double leucocitosValorAbsoluto;
	private String leucocitosUnidade;
	private String leucocitosValorDeReferencia;
	private String leucocitosObservacoes;
	
	private double bastonetesPercentual; //percentual sobre leucocitos
	private double bastonetesValorAbsoluto;
	private String bastonetesUnidade;
	private String bastonetesValorDeReferencia;
	private String bastonetesObservacoes;
	
	private double segmentadosPercentual; //percentual sobre leucocitos
	private double segmentadosValorAbsoluto;
	private String segmentadosUnidade;
	private String segmentadosValorDeReferencia;
	private String segmentadosObservacoes;
	
	private double eosinofilosPercentual; //percentual sobre leucocitos
	private double eosinofilosValorAbsoluto;
	private String eosinofilosUnidade;
	private String eosinofilosValorDeReferencia;
	private String eosinofilosObservacoes;
	
	private double monocitosPercentual; //percentual sobre leucocitos
	private double monocitosValorAbsoluto;
	private String monocitosUnidade;
	private String monocitosValorDeReferencia;
	private String monocitosObservacoes;
	
	private double linfocitosPercentual; //percentual sobre leucocitos
	private double linfocitosValorAbsoluto;
	private String linfocitosUnidade;
	private String linfocitosValorDeReferencia;
	private String linfocitosObservacoes;
		
	//CAMPOS DO ERITROGRAMA
		
	private String eritrocitosValorEncontrado;
	private String eritrocitosUnidade;
	private String eritrocitosValorDeReferencia;
	private String eritrocitosObservacoes;
	
	private String hemoglobinaValorEncontrado;
	private String hemoglobinaUnidade;
	private String hemoglobinaValorDeReferencia;
	private String hemoglobinaObservacoes;
	
	private String hematocritoValorEncontrado;
	private String hematocritoUnidade;
	private String hematocritoValorDeReferencia;
	private String hematocritoObservacoes;
	
	private String mcvValorEncontrado;
	private String mcvUnidade;
	private String mcvValorDeReferencia;
	private String mcvObservacoes;
	
	private String mchValorEncontrado;
	private String mchUnidade;
	private String mchValorDeReferencia;
	private String mchObservacoes;
	
	private String mchcValorEncontrado;
	private String mchcUnidade;
	private String mchcValorDeReferencia;
	private String mchcObservacoes;
	
	private double rdwValorEncontrado;
	private String rdwUnidade;
	private String rdwValorDeReferencia;
	private String rdwObservacoes;
	
	
	
	public void rotinaDeRecuperacaoDeHemograma()
	{
		
		//LEUCOGRAMA
		
		leucocitosValorAbsoluto = Double.parseDouble(listaDeResultado.get(0).getRESULT_VALOR_ENCONTRADO());
		leucocitosUnidade = listaDeResultado.get(0).getRESULT_UNIDADE();
		leucocitosValorDeReferencia = listaDeResultado.get(0).getRESULT_VALOR_REFERENCIA();
		leucocitosObservacoes = listaDeResultado.get(0).getRESULT_OBSERVACOES();
		
		bastonetesPercentual = Double.parseDouble(listaDeResultado.get(1).getRESULT_VALOR_ENCONTRADO());
		bastonetesValorAbsoluto = (bastonetesPercentual/100)*leucocitosValorAbsoluto; 
		bastonetesUnidade = listaDeResultado.get(1).getRESULT_UNIDADE();
		bastonetesValorDeReferencia = listaDeResultado.get(1).getRESULT_VALOR_REFERENCIA();
		bastonetesObservacoes = listaDeResultado.get(1).getRESULT_OBSERVACOES();
		
		segmentadosPercentual = Double.parseDouble(listaDeResultado.get(1).getRESULT_VALOR_ENCONTRADO());
		segmentadosValorAbsoluto = (segmentadosPercentual/100)*leucocitosValorAbsoluto; 
		segmentadosUnidade = listaDeResultado.get(1).getRESULT_UNIDADE();
		segmentadosValorDeReferencia = listaDeResultado.get(1).getRESULT_VALOR_REFERENCIA();
		segmentadosObservacoes = listaDeResultado.get(1).getRESULT_OBSERVACOES();
		
		eosinofilosPercentual = Double.parseDouble(listaDeResultado.get(2).getRESULT_VALOR_ENCONTRADO());
		eosinofilosValorAbsoluto = (eosinofilosPercentual/100)*leucocitosValorAbsoluto; 
		eosinofilosUnidade = listaDeResultado.get(2).getRESULT_UNIDADE();
		eosinofilosValorDeReferencia = listaDeResultado.get(2).getRESULT_VALOR_REFERENCIA();
		eosinofilosObservacoes = listaDeResultado.get(2).getRESULT_OBSERVACOES();
		
		monocitosPercentual = Double.parseDouble(listaDeResultado.get(3).getRESULT_VALOR_ENCONTRADO());
		monocitosValorAbsoluto = (monocitosPercentual/100)*leucocitosValorAbsoluto; 
		monocitosUnidade = listaDeResultado.get(3).getRESULT_UNIDADE();
		monocitosValorDeReferencia = listaDeResultado.get(3).getRESULT_VALOR_REFERENCIA();
		monocitosObservacoes = listaDeResultado.get(3).getRESULT_OBSERVACOES();
		
		linfocitosPercentual = Double.parseDouble(listaDeResultado.get(4).getRESULT_VALOR_ENCONTRADO());
		linfocitosValorAbsoluto = (linfocitosPercentual/100)*leucocitosValorAbsoluto; 
		linfocitosUnidade = listaDeResultado.get(4).getRESULT_UNIDADE();
		linfocitosValorDeReferencia = listaDeResultado.get(4).getRESULT_VALOR_REFERENCIA();
		linfocitosObservacoes = listaDeResultado.get(4).getRESULT_OBSERVACOES();
		
		//ERITROGRAMA
		
		eritrocitosValorEncontrado = listaDeResultado.get(5).getRESULT_VALOR_ENCONTRADO();
		eritrocitosUnidade = listaDeResultado.get(5).getRESULT_UNIDADE();
		eritrocitosValorDeReferencia = listaDeResultado.get(5).getRESULT_VALOR_REFERENCIA();
		eritrocitosObservacoes = listaDeResultado.get(5).getRESULT_OBSERVACOES();
		
		hemoglobinaValorEncontrado = listaDeResultado.get(6).getRESULT_VALOR_ENCONTRADO();
		hemoglobinaUnidade = listaDeResultado.get(6).getRESULT_UNIDADE();
		hemoglobinaValorDeReferencia = listaDeResultado.get(6).getRESULT_VALOR_REFERENCIA();
		hemoglobinaObservacoes = listaDeResultado.get(6).getRESULT_OBSERVACOES();
		
		hematocritoValorEncontrado = listaDeResultado.get(7).getRESULT_VALOR_ENCONTRADO();
		hematocritoUnidade = listaDeResultado.get(7).getRESULT_UNIDADE();
		hematocritoValorDeReferencia = listaDeResultado.get(7).getRESULT_VALOR_REFERENCIA();
		hematocritoObservacoes = listaDeResultado.get(7).getRESULT_OBSERVACOES();
		
		mcvValorEncontrado = listaDeResultado.get(7).getRESULT_VALOR_ENCONTRADO();
		mcvUnidade = listaDeResultado.get(7).getRESULT_UNIDADE();
		mcvValorDeReferencia = listaDeResultado.get(7).getRESULT_VALOR_REFERENCIA();
		mcvObservacoes = listaDeResultado.get(7).getRESULT_OBSERVACOES();
		
		mchValorEncontrado = listaDeResultado.get(7).getRESULT_VALOR_ENCONTRADO();
		mchUnidade = listaDeResultado.get(7).getRESULT_UNIDADE();
		mchValorDeReferencia = listaDeResultado.get(7).getRESULT_VALOR_REFERENCIA();
		mchObservacoes = listaDeResultado.get(7).getRESULT_OBSERVACOES();
		
		mchcValorEncontrado = listaDeResultado.get(7).getRESULT_VALOR_ENCONTRADO();
		mchcUnidade = listaDeResultado.get(7).getRESULT_UNIDADE();
		mchcValorDeReferencia = listaDeResultado.get(7).getRESULT_VALOR_REFERENCIA();
		mchcObservacoes = listaDeResultado.get(7).getRESULT_OBSERVACOES();
		
		rdwValorEncontrado = Double.parseDouble(listaDeResultado.get(7).getRESULT_VALOR_ENCONTRADO());
		rdwUnidade = listaDeResultado.get(7).getRESULT_UNIDADE();
		rdwValorDeReferencia = listaDeResultado.get(7).getRESULT_VALOR_REFERENCIA();
		rdwObservacoes = listaDeResultado.get(7).getRESULT_OBSERVACOES();
		
		
	}
	
	//GETTERS AND SETTERS

	public Exame getExameSelecionado() {
		return exameSelecionado;
	}

	public void setExameSelecionado(Exame exameSelecionado) {
		this.exameSelecionado = exameSelecionado;
	}

	public double getLeucocitosValorAbsoluto() {
		return leucocitosValorAbsoluto;
	}

	public void setLeucocitosValorAbsoluto(double leucocitosValorAbsoluto) {
		this.leucocitosValorAbsoluto = leucocitosValorAbsoluto;
	}

	public String getLeucocitosUnidade() {
		return leucocitosUnidade;
	}

	public void setLeucocitosUnidade(String leucocitosUnidade) {
		this.leucocitosUnidade = leucocitosUnidade;
	}

	public String getLeucocitosValorDeReferencia() {
		return leucocitosValorDeReferencia;
	}

	public void setLeucocitosValorDeReferencia(String leucocitosValorDeReferencia) {
		this.leucocitosValorDeReferencia = leucocitosValorDeReferencia;
	}

	public String getLeucocitosObservacoes() {
		return leucocitosObservacoes;
	}

	public void setLeucocitosObservacoes(String leucocitosObservacoes) {
		this.leucocitosObservacoes = leucocitosObservacoes;
	}

	public double getBastonetesPercentual() {
		return bastonetesPercentual;
	}

	public void setBastonetesPercentual(double bastonetesPercentual) {
		this.bastonetesPercentual = bastonetesPercentual;
	}

	public double getBastonetesValorAbsoluto() {
		return bastonetesValorAbsoluto;
	}

	public void setBastonetesValorAbsoluto(double bastonetesValorAbsoluto) {
		this.bastonetesValorAbsoluto = bastonetesValorAbsoluto;
	}

	public String getBastonetesUnidade() {
		return bastonetesUnidade;
	}

	public void setBastonetesUnidade(String bastonetesUnidade) {
		this.bastonetesUnidade = bastonetesUnidade;
	}

	public String getBastonetesValorDeReferencia() {
		return bastonetesValorDeReferencia;
	}

	public void setBastonetesValorDeReferencia(String bastonetesValorDeReferencia) {
		this.bastonetesValorDeReferencia = bastonetesValorDeReferencia;
	}

	public String getBastonetesObservacoes() {
		return bastonetesObservacoes;
	}

	public void setBastonetesObservacoes(String bastonetesObservacoes) {
		this.bastonetesObservacoes = bastonetesObservacoes;
	}

	public double getSegmentadosPercentual() {
		return segmentadosPercentual;
	}

	public void setSegmentadosPercentual(double segmentadosPercentual) {
		this.segmentadosPercentual = segmentadosPercentual;
	}

	public double getSegmentadosValorAbsoluto() {
		return segmentadosValorAbsoluto;
	}

	public void setSegmentadosValorAbsoluto(double segmentadosValorAbsoluto) {
		this.segmentadosValorAbsoluto = segmentadosValorAbsoluto;
	}

	public String getSegmentadosUnidade() {
		return segmentadosUnidade;
	}

	public void setSegmentadosUnidade(String segmentadosUnidade) {
		this.segmentadosUnidade = segmentadosUnidade;
	}

	public String getSegmentadosValorDeReferencia() {
		return segmentadosValorDeReferencia;
	}

	public void setSegmentadosValorDeReferencia(String segmentadosValorDeReferencia) {
		this.segmentadosValorDeReferencia = segmentadosValorDeReferencia;
	}

	public String getSegmentadosObservacoes() {
		return segmentadosObservacoes;
	}

	public void setSegmentadosObservacoes(String segmentadosObservacoes) {
		this.segmentadosObservacoes = segmentadosObservacoes;
	}

	public double getEosinofilosPercentual() {
		return eosinofilosPercentual;
	}

	public void setEosinofilosPercentual(double eosinofilosPercentual) {
		this.eosinofilosPercentual = eosinofilosPercentual;
	}

	public double getEosinofilosValorAbsoluto() {
		return eosinofilosValorAbsoluto;
	}

	public void setEosinofilosValorAbsoluto(double eosinofilosValorAbsoluto) {
		this.eosinofilosValorAbsoluto = eosinofilosValorAbsoluto;
	}

	public String getEosinofilosUnidade() {
		return eosinofilosUnidade;
	}

	public void setEosinofilosUnidade(String eosinofilosUnidade) {
		this.eosinofilosUnidade = eosinofilosUnidade;
	}

	public String getEosinofilosValorDeReferencia() {
		return eosinofilosValorDeReferencia;
	}

	public void setEosinofilosValorDeReferencia(String eosinofilosValorDeReferencia) {
		this.eosinofilosValorDeReferencia = eosinofilosValorDeReferencia;
	}

	public String getEosinofilosObservacoes() {
		return eosinofilosObservacoes;
	}

	public void setEosinofilosObservacoes(String eosinofilosObservacoes) {
		this.eosinofilosObservacoes = eosinofilosObservacoes;
	}

	public double getMonocitosPercentual() {
		return monocitosPercentual;
	}

	public void setMonocitosPercentual(double monocitosPercentual) {
		this.monocitosPercentual = monocitosPercentual;
	}

	public double getMonocitosValorAbsoluto() {
		return monocitosValorAbsoluto;
	}

	public void setMonocitosValorAbsoluto(double monocitosValorAbsoluto) {
		this.monocitosValorAbsoluto = monocitosValorAbsoluto;
	}

	public String getMonocitosUnidade() {
		return monocitosUnidade;
	}

	public void setMonocitosUnidade(String monocitosUnidade) {
		this.monocitosUnidade = monocitosUnidade;
	}

	public String getMonocitosValorDeReferencia() {
		return monocitosValorDeReferencia;
	}

	public void setMonocitosValorDeReferencia(String monocitosValorDeReferencia) {
		this.monocitosValorDeReferencia = monocitosValorDeReferencia;
	}

	public String getMonocitosObservacoes() {
		return monocitosObservacoes;
	}

	public void setMonocitosObservacoes(String monocitosObservacoes) {
		this.monocitosObservacoes = monocitosObservacoes;
	}

	public double getLinfocitosPercentual() {
		return linfocitosPercentual;
	}

	public void setLinfocitosPercentual(double linfocitosPercentual) {
		this.linfocitosPercentual = linfocitosPercentual;
	}

	public double getLinfocitosValorAbsoluto() {
		return linfocitosValorAbsoluto;
	}

	public void setLinfocitosValorAbsoluto(double linfocitosValorAbsoluto) {
		this.linfocitosValorAbsoluto = linfocitosValorAbsoluto;
	}

	public String getLinfocitosUnidade() {
		return linfocitosUnidade;
	}

	public void setLinfocitosUnidade(String linfocitosUnidade) {
		this.linfocitosUnidade = linfocitosUnidade;
	}

	public String getLinfocitosValorDeReferencia() {
		return linfocitosValorDeReferencia;
	}

	public void setLinfocitosValorDeReferencia(String linfocitosValorDeReferencia) {
		this.linfocitosValorDeReferencia = linfocitosValorDeReferencia;
	}

	public String getLinfocitosObservacoes() {
		return linfocitosObservacoes;
	}

	public void setLinfocitosObservacoes(String linfocitosObservacoes) {
		this.linfocitosObservacoes = linfocitosObservacoes;
	}

	public String getEritrocitosValorEncontrado() {
		return eritrocitosValorEncontrado;
	}

	public void setEritrocitosValorEncontrado(String eritrocitosValorEncontrado) {
		this.eritrocitosValorEncontrado = eritrocitosValorEncontrado;
	}

	public String getEritrocitosUnidade() {
		return eritrocitosUnidade;
	}

	public void setEritrocitosUnidade(String eritrocitosUnidade) {
		this.eritrocitosUnidade = eritrocitosUnidade;
	}

	public String getEritrocitosValorDeReferencia() {
		return eritrocitosValorDeReferencia;
	}

	public void setEritrocitosValorDeReferencia(String eritrocitosValorDeReferencia) {
		this.eritrocitosValorDeReferencia = eritrocitosValorDeReferencia;
	}

	public String getEritrocitosObservacoes() {
		return eritrocitosObservacoes;
	}

	public void setEritrocitosObservacoes(String eritrocitosObservacoes) {
		this.eritrocitosObservacoes = eritrocitosObservacoes;
	}

	public String getHemoglobinaValorEncontrado() {
		return hemoglobinaValorEncontrado;
	}

	public void setHemoglobinaValorEncontrado(String hemoglobinaValorEncontrado) {
		this.hemoglobinaValorEncontrado = hemoglobinaValorEncontrado;
	}

	public String getHemoglobinaUnidade() {
		return hemoglobinaUnidade;
	}

	public void setHemoglobinaUnidade(String hemoglobinaUnidade) {
		this.hemoglobinaUnidade = hemoglobinaUnidade;
	}

	public String getHemoglobinaValorDeReferencia() {
		return hemoglobinaValorDeReferencia;
	}

	public void setHemoglobinaValorDeReferencia(String hemoglobinaValorDeReferencia) {
		this.hemoglobinaValorDeReferencia = hemoglobinaValorDeReferencia;
	}

	public String getHemoglobinaObservacoes() {
		return hemoglobinaObservacoes;
	}

	public void setHemoglobinaObservacoes(String hemoglobinaObservacoes) {
		this.hemoglobinaObservacoes = hemoglobinaObservacoes;
	}

	public String getHematocritoValorEncontrado() {
		return hematocritoValorEncontrado;
	}

	public void setHematocritoValorEncontrado(String hematocritoValorEncontrado) {
		this.hematocritoValorEncontrado = hematocritoValorEncontrado;
	}

	public String getHematocritoUnidade() {
		return hematocritoUnidade;
	}

	public void setHematocritoUnidade(String hematocritoUnidade) {
		this.hematocritoUnidade = hematocritoUnidade;
	}

	public String getHematocritoValorDeReferencia() {
		return hematocritoValorDeReferencia;
	}

	public void setHematocritoValorDeReferencia(String hematocritoValorDeReferencia) {
		this.hematocritoValorDeReferencia = hematocritoValorDeReferencia;
	}

	public String getHematocritoObservacoes() {
		return hematocritoObservacoes;
	}

	public void setHematocritoObservacoes(String hematocritoObservacoes) {
		this.hematocritoObservacoes = hematocritoObservacoes;
	}

	public String getMcvValorEncontrado() {
		return mcvValorEncontrado;
	}

	public void setMcvValorEncontrado(String mcvValorEncontrado) {
		this.mcvValorEncontrado = mcvValorEncontrado;
	}

	public String getMcvUnidade() {
		return mcvUnidade;
	}

	public void setMcvUnidade(String mcvUnidade) {
		this.mcvUnidade = mcvUnidade;
	}

	public String getMcvValorDeReferencia() {
		return mcvValorDeReferencia;
	}

	public void setMcvValorDeReferencia(String mcvValorDeReferencia) {
		this.mcvValorDeReferencia = mcvValorDeReferencia;
	}

	public String getMcvObservacoes() {
		return mcvObservacoes;
	}

	public void setMcvObservacoes(String mcvObservacoes) {
		this.mcvObservacoes = mcvObservacoes;
	}

	public String getMchValorEncontrado() {
		return mchValorEncontrado;
	}

	public void setMchValorEncontrado(String mchValorEncontrado) {
		this.mchValorEncontrado = mchValorEncontrado;
	}

	public String getMchUnidade() {
		return mchUnidade;
	}

	public void setMchUnidade(String mchUnidade) {
		this.mchUnidade = mchUnidade;
	}

	public String getMchValorDeReferencia() {
		return mchValorDeReferencia;
	}

	public void setMchValorDeReferencia(String mchValorDeReferencia) {
		this.mchValorDeReferencia = mchValorDeReferencia;
	}

	public String getMchObservacoes() {
		return mchObservacoes;
	}

	public void setMchObservacoes(String mchObservacoes) {
		this.mchObservacoes = mchObservacoes;
	}

	public String getMchcValorEncontrado() {
		return mchcValorEncontrado;
	}

	public void setMchcValorEncontrado(String mchcValorEncontrado) {
		this.mchcValorEncontrado = mchcValorEncontrado;
	}

	public String getMchcUnidade() {
		return mchcUnidade;
	}

	public void setMchcUnidade(String mchcUnidade) {
		this.mchcUnidade = mchcUnidade;
	}

	public String getMchcValorDeReferencia() {
		return mchcValorDeReferencia;
	}

	public void setMchcValorDeReferencia(String mchcValorDeReferencia) {
		this.mchcValorDeReferencia = mchcValorDeReferencia;
	}

	public String getMchcObservacoes() {
		return mchcObservacoes;
	}

	public void setMchcObservacoes(String mchcObservacoes) {
		this.mchcObservacoes = mchcObservacoes;
	}

	public double getRdwValorEncontrado() {
		return rdwValorEncontrado;
	}

	public void setRdwValorEncontrado(double rdwValorEncontrado) {
		this.rdwValorEncontrado = rdwValorEncontrado;
	}

	public String getRdwUnidade() {
		return rdwUnidade;
	}

	public void setRdwUnidade(String rdwUnidade) {
		this.rdwUnidade = rdwUnidade;
	}

	public String getRdwValorDeReferencia() {
		return rdwValorDeReferencia;
	}

	public void setRdwValorDeReferencia(String rdwValorDeReferencia) {
		this.rdwValorDeReferencia = rdwValorDeReferencia;
	}

	public String getRdwObservacoes() {
		return rdwObservacoes;
	}

	public void setRdwObservacoes(String rdwObservacoes) {
		this.rdwObservacoes = rdwObservacoes;
	}
	
	
	
	


	}
