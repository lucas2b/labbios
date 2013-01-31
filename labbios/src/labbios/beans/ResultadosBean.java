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

public class ResultadosBean {
	
	//Atributos de controle
	private Exame exameSelecionado;
	private boolean flagNovaEntrada;
	private boolean tipoHemograma;
	
	//DAOS usados
	private DadosDoExameDAO dadosDoExameDAO = new DadosDoExameDAO();
	private ResultadoDAO resultadoDAO = new ResultadoDAO();
	private ExameDAO exameDAO = new ExameDAO();
	
	
	//Listas de auxílio
	private List<Resultado> listaDeExibicao = new LinkedList<Resultado>();
	private List<DadosDoExameSuporte> tabelaMolde; //Molde da tabela de exames recuperado
	List<Integer> indiceDoExame = new LinkedList<Integer>();
	
	
	//Função de início
	public String inicioEntradaResultado() throws SQLException, ClassNotFoundException
	{
		
		if( Integer.valueOf(exameSelecionado.getEXAME_ID()) == 0 )
		{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"A solicitação ainda não foi salva! Clique em salvar e retorne para entrar resultados!", ""));
			return null;
		}
		
		if(exameSelecionado.getCAD_EXAME().getCAD_EXAME_NOME().equals("HEMOGRAMA"))
			tipoHemograma = true;
		else
			tipoHemograma = false;
		
		
		if(resultadoDAO.verificaEntradaExistente(exameSelecionado))
		{
				flagNovaEntrada = false;
				
				listaDeExibicao = resultadoDAO.recuperarResultado(exameSelecionado);
				
				for(Resultado resultado : listaDeExibicao)
					indiceDoExame.add(resultado.getRESULT_ID());
				
				if(tipoHemograma)
					rotinaDeRecuperacaoDeHemograma();
		}
		else
		{
				flagNovaEntrada = true;
				
				tabelaMolde = dadosDoExameDAO.recuperarTabela(exameSelecionado.getCAD_EXAME());
				
				for(DadosDoExameSuporte molde: tabelaMolde)
				{
					Resultado resultado = new Resultado();
					resultado.setEXAME(exameDAO.buscarExamePorID(exameSelecionado.getEXAME_ID()));
					resultado.setRESULT_PARAMETRO(molde.getParametro());
					resultado.setRESULT_UNIDADE(molde.getUnidade());
					resultado.setRESULT_VALOR_REFERENCIA(molde.getReferencia());
					//resultado.setRESULT_VALOR_ENCONTRADO();
					resultado.setRESULT_OBSERVACOES("");
					listaDeExibicao.add(resultado);
					indiceDoExame.add(1);
				}
				
				if(tipoHemograma)
					rotinaPovoamentoDeHemograma();
				
		}				
		
		if(tipoHemograma)
			return "resultadoTipoHemograma";
		 else
			 return "entradaDeResultadosTipoComum";
		
	}
	
	public String botaoGravarResultados() throws SQLException, ClassNotFoundException
	{	
		try
		{
			if(tipoHemograma)
			{
				Double somatorioPercentual = bastonetesPercentual +
											segmentadosPercentual +
											eosinofilosPercentual + 
											monocitosPercentual + 
											linfocitosPercentual;
				
				if((somatorioPercentual != 100) || (rdwValorEncontrado < 15))
				{
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Os valores percentuais de Leucócitos não totalizam 100%, ou RDW abaixo de 15!", ""));
					throw new Exception();  
				}
				else
				{				
					rotinaDeGravacaoDeHemograma();
				}
			}
			
			
			if(flagNovaEntrada)
				resultadoDAO.inserirNovoResultado(listaDeExibicao);
			else
				resultadoDAO.updateResultadoExistente(listaDeExibicao);
			
			return retornarParaSolicitacao();	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			exameSelecionado = null;
			tipoHemograma = false;
		}
		
		return null;
	}
	
	public String retornarParaSolicitacao()
	{
		//exameSelecionado = null;
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
		
	private double eritrocitosValorEncontrado;
	private String eritrocitosUnidade;
	private String eritrocitosValorDeReferencia;
	private String eritrocitosObservacoes;
	
	private double hemoglobinaValorEncontrado;
	private String hemoglobinaUnidade;
	private String hemoglobinaValorDeReferencia;
	private String hemoglobinaObservacoes;
	
	private double hematocritoValorEncontrado;
	private String hematocritoUnidade;
	private String hematocritoValorDeReferencia;
	private String hematocritoObservacoes;
	
	private double mcvValorEncontrado;
	private String mcvUnidade;
	private String mcvValorDeReferencia;
	private String mcvObservacoes;
	
	private double mchValorEncontrado;
	private String mchUnidade;
	private String mchValorDeReferencia;
	private String mchObservacoes;
	
	private double mchcValorEncontrado;
	private String mchcUnidade;
	private String mchcValorDeReferencia;
	private String mchcObservacoes;
	
	private double rdwValorEncontrado;
	private String rdwUnidade;
	private String rdwValorDeReferencia;
	private String rdwObservacoes;
	
	public void rotinaPovoamentoDeHemograma()
	{	
		leucocitosValorDeReferencia = listaDeExibicao.get(0).getRESULT_VALOR_REFERENCIA();
		leucocitosUnidade = listaDeExibicao.get(0).getRESULT_UNIDADE();
		
		bastonetesValorDeReferencia = listaDeExibicao.get(1).getRESULT_VALOR_REFERENCIA();
		bastonetesUnidade = listaDeExibicao.get(1).getRESULT_UNIDADE();
		
		segmentadosValorDeReferencia = listaDeExibicao.get(2).getRESULT_VALOR_REFERENCIA();
		segmentadosUnidade = listaDeExibicao.get(2).getRESULT_UNIDADE();
		
		eosinofilosValorDeReferencia = listaDeExibicao.get(3).getRESULT_VALOR_REFERENCIA();
		eosinofilosUnidade = listaDeExibicao.get(3).getRESULT_UNIDADE();
		
		monocitosValorDeReferencia = listaDeExibicao.get(4).getRESULT_VALOR_REFERENCIA();
		monocitosUnidade = listaDeExibicao.get(4).getRESULT_UNIDADE();
		
		linfocitosValorDeReferencia = listaDeExibicao.get(5).getRESULT_VALOR_REFERENCIA();
		linfocitosUnidade = listaDeExibicao.get(5).getRESULT_UNIDADE();
		
		eritrocitosValorDeReferencia = listaDeExibicao.get(6).getRESULT_VALOR_REFERENCIA();
		eritrocitosUnidade = listaDeExibicao.get(6).getRESULT_UNIDADE();
		
		hemoglobinaValorDeReferencia = listaDeExibicao.get(7).getRESULT_VALOR_REFERENCIA();
		hemoglobinaUnidade = listaDeExibicao.get(7).getRESULT_UNIDADE();
		
		hematocritoValorDeReferencia = listaDeExibicao.get(8).getRESULT_VALOR_REFERENCIA();
		hematocritoUnidade = listaDeExibicao.get(8).getRESULT_UNIDADE();
		
		mcvValorDeReferencia = listaDeExibicao.get(9).getRESULT_VALOR_REFERENCIA();
		mcvUnidade = listaDeExibicao.get(9).getRESULT_UNIDADE();
		
		mchValorDeReferencia = listaDeExibicao.get(10).getRESULT_VALOR_REFERENCIA();
		mchUnidade = listaDeExibicao.get(10).getRESULT_UNIDADE();
		
		mchcValorDeReferencia = listaDeExibicao.get(11).getRESULT_VALOR_REFERENCIA();
		mchcUnidade = listaDeExibicao.get(11).getRESULT_UNIDADE();
		
		rdwValorDeReferencia = listaDeExibicao.get(12).getRESULT_VALOR_REFERENCIA();
		rdwUnidade = listaDeExibicao.get(12).getRESULT_UNIDADE();
	}
	
	public void rotinaDeRecuperacaoDeHemograma()
	{
		
		//LEUCOGRAMA
		
		leucocitosValorAbsoluto = listaDeExibicao.get(0).getRESULT_VALOR_ENCONTRADO();
		leucocitosUnidade = listaDeExibicao.get(0).getRESULT_UNIDADE();
		leucocitosValorDeReferencia = listaDeExibicao.get(0).getRESULT_VALOR_REFERENCIA();
		leucocitosObservacoes = listaDeExibicao.get(0).getRESULT_OBSERVACOES();
		
		bastonetesPercentual = listaDeExibicao.get(1).getRESULT_VALOR_ENCONTRADO();
		bastonetesValorAbsoluto = (bastonetesPercentual/100)*leucocitosValorAbsoluto; 
		bastonetesUnidade = listaDeExibicao.get(1).getRESULT_UNIDADE();
		bastonetesValorDeReferencia = listaDeExibicao.get(1).getRESULT_VALOR_REFERENCIA();
		bastonetesObservacoes = listaDeExibicao.get(1).getRESULT_OBSERVACOES();
		
		segmentadosPercentual = listaDeExibicao.get(2).getRESULT_VALOR_ENCONTRADO();
		segmentadosValorAbsoluto = (segmentadosPercentual/100)*leucocitosValorAbsoluto; 
		segmentadosUnidade = listaDeExibicao.get(2).getRESULT_UNIDADE();
		segmentadosValorDeReferencia = listaDeExibicao.get(2).getRESULT_VALOR_REFERENCIA();
		segmentadosObservacoes = listaDeExibicao.get(2).getRESULT_OBSERVACOES();
		
		eosinofilosPercentual = listaDeExibicao.get(3).getRESULT_VALOR_ENCONTRADO();
		eosinofilosValorAbsoluto = (eosinofilosPercentual/100)*leucocitosValorAbsoluto; 
		eosinofilosUnidade = listaDeExibicao.get(3).getRESULT_UNIDADE();
		eosinofilosValorDeReferencia = listaDeExibicao.get(3).getRESULT_VALOR_REFERENCIA();
		eosinofilosObservacoes = listaDeExibicao.get(3).getRESULT_OBSERVACOES();
		
		monocitosPercentual = listaDeExibicao.get(4).getRESULT_VALOR_ENCONTRADO();
		monocitosValorAbsoluto = (monocitosPercentual/100)*leucocitosValorAbsoluto; 
		monocitosUnidade = listaDeExibicao.get(4).getRESULT_UNIDADE();
		monocitosValorDeReferencia = listaDeExibicao.get(4).getRESULT_VALOR_REFERENCIA();
		monocitosObservacoes = listaDeExibicao.get(4).getRESULT_OBSERVACOES();
		
		linfocitosPercentual = listaDeExibicao.get(5).getRESULT_VALOR_ENCONTRADO();
		linfocitosValorAbsoluto = (linfocitosPercentual/100)*leucocitosValorAbsoluto; 
		linfocitosUnidade = listaDeExibicao.get(5).getRESULT_UNIDADE();
		linfocitosValorDeReferencia = listaDeExibicao.get(5).getRESULT_VALOR_REFERENCIA();
		linfocitosObservacoes = listaDeExibicao.get(5).getRESULT_OBSERVACOES();
		
		//ERITROGRAMA
		
		eritrocitosValorEncontrado = listaDeExibicao.get(6).getRESULT_VALOR_ENCONTRADO();
		eritrocitosUnidade = listaDeExibicao.get(6).getRESULT_UNIDADE();
		eritrocitosValorDeReferencia = listaDeExibicao.get(6).getRESULT_VALOR_REFERENCIA();
		eritrocitosObservacoes = listaDeExibicao.get(6).getRESULT_OBSERVACOES();
		
		hemoglobinaValorEncontrado = listaDeExibicao.get(7).getRESULT_VALOR_ENCONTRADO();
		hemoglobinaUnidade = listaDeExibicao.get(7).getRESULT_UNIDADE();
		hemoglobinaValorDeReferencia = listaDeExibicao.get(7).getRESULT_VALOR_REFERENCIA();
		hemoglobinaObservacoes = listaDeExibicao.get(7).getRESULT_OBSERVACOES();
		
		hematocritoValorEncontrado = listaDeExibicao.get(8).getRESULT_VALOR_ENCONTRADO();
		hematocritoUnidade = listaDeExibicao.get(8).getRESULT_UNIDADE();
		hematocritoValorDeReferencia = listaDeExibicao.get(8).getRESULT_VALOR_REFERENCIA();
		hematocritoObservacoes = listaDeExibicao.get(8).getRESULT_OBSERVACOES();
		
		mcvValorEncontrado = listaDeExibicao.get(9).getRESULT_VALOR_ENCONTRADO();
		mcvUnidade = listaDeExibicao.get(9).getRESULT_UNIDADE();
		mcvValorDeReferencia = listaDeExibicao.get(9).getRESULT_VALOR_REFERENCIA();
		mcvObservacoes = listaDeExibicao.get(9).getRESULT_OBSERVACOES();
		
		mchValorEncontrado = listaDeExibicao.get(10).getRESULT_VALOR_ENCONTRADO();
		mchUnidade = listaDeExibicao.get(10).getRESULT_UNIDADE();
		mchValorDeReferencia = listaDeExibicao.get(10).getRESULT_VALOR_REFERENCIA();
		mchObservacoes = listaDeExibicao.get(10).getRESULT_OBSERVACOES();
		
		mchcValorEncontrado = listaDeExibicao.get(11).getRESULT_VALOR_ENCONTRADO();
		mchcUnidade = listaDeExibicao.get(11).getRESULT_UNIDADE();
		mchcValorDeReferencia = listaDeExibicao.get(11).getRESULT_VALOR_REFERENCIA();
		mchcObservacoes = listaDeExibicao.get(11).getRESULT_OBSERVACOES();
		
		rdwValorEncontrado = listaDeExibicao.get(12).getRESULT_VALOR_ENCONTRADO();
		rdwUnidade = listaDeExibicao.get(12).getRESULT_UNIDADE();
		rdwValorDeReferencia = listaDeExibicao.get(12).getRESULT_VALOR_REFERENCIA();
		rdwObservacoes = listaDeExibicao.get(12).getRESULT_OBSERVACOES();
		
		
	}
	
	public void rotinaDeGravacaoDeHemograma()
	{
		//preparar lista de exibição com campos da tela
			
		Resultado resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("LEUCOCITOS");
		resultado.setRESULT_ID(indiceDoExame.get(0));
		resultado.setRESULT_VALOR_ENCONTRADO(leucocitosValorAbsoluto);
		resultado.setRESULT_UNIDADE(leucocitosUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(leucocitosValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(leucocitosObservacoes);
		listaDeExibicao.add(resultado);
		
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("BASTONETES");
		resultado.setRESULT_ID(indiceDoExame.get(1));
		resultado.setRESULT_VALOR_ENCONTRADO(bastonetesPercentual);
		resultado.setRESULT_UNIDADE(bastonetesUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(bastonetesValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(bastonetesObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("SEGMENTADOS");
		resultado.setRESULT_ID(indiceDoExame.get(2));
		resultado.setRESULT_VALOR_ENCONTRADO(segmentadosPercentual);
		resultado.setRESULT_UNIDADE(segmentadosUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(segmentadosValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(segmentadosObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("EOSINOFILOS");
		resultado.setRESULT_ID(indiceDoExame.get(3));
		resultado.setRESULT_VALOR_ENCONTRADO(eosinofilosPercentual);
		resultado.setRESULT_UNIDADE(eosinofilosUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(eosinofilosValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(eosinofilosObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("MONOCITOS");
		resultado.setRESULT_ID(indiceDoExame.get(4));
		resultado.setRESULT_VALOR_ENCONTRADO(monocitosPercentual);
		resultado.setRESULT_UNIDADE(monocitosUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(monocitosValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(monocitosObservacoes);
		listaDeExibicao.add(resultado);
		
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("LINFOCITOS");
		resultado.setRESULT_ID(indiceDoExame.get(5));
		resultado.setRESULT_VALOR_ENCONTRADO(linfocitosPercentual);
		resultado.setRESULT_UNIDADE(linfocitosUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(linfocitosValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(linfocitosObservacoes);
		listaDeExibicao.add(resultado);
		
			
		//CAMPOS DO ERITROGRAMA
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("ERITROCITOS");
		resultado.setRESULT_ID(indiceDoExame.get(6));
		resultado.setRESULT_VALOR_ENCONTRADO(eritrocitosValorEncontrado);
		resultado.setRESULT_UNIDADE(eritrocitosUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(eritrocitosValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(eritrocitosObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("HEMOGLOBINA");
		resultado.setRESULT_ID(indiceDoExame.get(7));
		resultado.setRESULT_VALOR_ENCONTRADO(hemoglobinaValorEncontrado);
		resultado.setRESULT_UNIDADE(hemoglobinaUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(hemoglobinaValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(hemoglobinaObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("HEMATOCRITO");
		resultado.setRESULT_ID(indiceDoExame.get(8));
		resultado.setRESULT_VALOR_ENCONTRADO(hematocritoValorEncontrado);
		resultado.setRESULT_UNIDADE(hematocritoUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(hematocritoValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(hematocritoObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("MCV");
		resultado.setRESULT_ID(indiceDoExame.get(9));
		resultado.setRESULT_VALOR_ENCONTRADO(mcvValorEncontrado);
		resultado.setRESULT_UNIDADE(mcvUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(mcvValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(mcvObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("MCH");
		resultado.setRESULT_ID(indiceDoExame.get(10));
		resultado.setRESULT_VALOR_ENCONTRADO(mchValorEncontrado);
		resultado.setRESULT_UNIDADE(mchUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(mchValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(mchObservacoes);
		listaDeExibicao.add(resultado);
		
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("MCHC");
		resultado.setRESULT_ID(indiceDoExame.get(11));
		resultado.setRESULT_VALOR_ENCONTRADO(mchcValorEncontrado);
		resultado.setRESULT_UNIDADE(mchcUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(mchcValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(mchcObservacoes);
		listaDeExibicao.add(resultado);
		
		resultado = new Resultado();
		resultado.setEXAME(exameSelecionado);
		resultado.setRESULT_PARAMETRO("RDW");
		resultado.setRESULT_ID(indiceDoExame.get(12));
		resultado.setRESULT_VALOR_ENCONTRADO(rdwValorEncontrado);
		resultado.setRESULT_UNIDADE(rdwUnidade);
		resultado.setRESULT_VALOR_REFERENCIA(rdwValorDeReferencia);
		resultado.setRESULT_OBSERVACOES(rdwObservacoes);
		listaDeExibicao.add(resultado);
		
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

	public double getEritrocitosValorEncontrado() {
		return eritrocitosValorEncontrado;
	}

	public void setEritrocitosValorEncontrado(double eritrocitosValorEncontrado) {
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

	public double getHemoglobinaValorEncontrado() {
		return hemoglobinaValorEncontrado;
	}

	public void setHemoglobinaValorEncontrado(double hemoglobinaValorEncontrado) {
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

	public double getHematocritoValorEncontrado() {
		return hematocritoValorEncontrado;
	}

	public void setHematocritoValorEncontrado(double hematocritoValorEncontrado) {
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

	public double getMcvValorEncontrado() {
		return mcvValorEncontrado;
	}

	public void setMcvValorEncontrado(double mcvValorEncontrado) {
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

	public double getMchValorEncontrado() {
		return mchValorEncontrado;
	}

	public void setMchValorEncontrado(double mchValorEncontrado) {
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

	public double getMchcValorEncontrado() {
		return mchcValorEncontrado;
	}

	public void setMchcValorEncontrado(double mchcValorEncontrado) {
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

	public List<Resultado> getListaDeExibicao() {
		return listaDeExibicao;
	}

	public void setListaDeExibicao(List<Resultado> listaDeExibicao) {
		this.listaDeExibicao = listaDeExibicao;
	}
	
}
