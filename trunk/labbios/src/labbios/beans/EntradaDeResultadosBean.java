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
	
	private int leucocitosValorAbsoluto;
	private String leucocitosUnidade;
	private String leucocitosValorDeReferencia;
	private String leucocitosObservacoes;
	
	private int bastonetesPercentual; //percentual sobre leucocitos
	private int bastonetesValorAbsoluto;
	private String bastonetesUnidade;
	private String bastonetesValorDeReferencia;
	private String bastonetesObservacoes;
	
	private int segmentadosPercentual; //percentual sobre leucocitos
	private int segmentadosValorAbsoluto;
	private String segmentadosUnidade;
	private String segmentadosValorDeReferencia;
	private String segmentadosObservacoes;
	
	private int eosinofilosPercentual; //percentual sobre leucocitos
	private int eosinofilosValorAbsoluto;
	private String eosinofilosUnidade;
	private String eosinofilosValorDeReferencia;
	private String eosinofilosObservacoes;
	
	private int monocitosPercentual; //percentual sobre leucocitos
	private int monocitosValorAbsoluto;
	private String monocitosUnidade;
	private String monocitosValorDeReferencia;
	private String monocitosObservacoes;
	
	private int linfocitosPercentual; //percentual sobre leucocitos
	private int linfocitosValorAbsoluto;
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
	
	private int rdwValorEncontrado;
	private String rdwUnidade;
	private String rdwValorDeReferencia;
	private String rdwObservacoes;
	
	
	
	public void rotinaDeRecuperacaoDeHemograma()
	{
		
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
		return listaDeExibicao;
	}
	public void setListaSuporte(List<Resultado> listaSuporte) {
		this.listaDeExibicao = listaSuporte;
	}

	
	//GETTERS AND SETTERS HEMOGRAMA
	
	public List<Resultado> getListaDeExibicao() {
		return listaDeExibicao;
	}

	public void setListaDeExibicao(List<Resultado> listaDeExibicao) {
		this.listaDeExibicao = listaDeExibicao;
	}

	public int getLeucocitosValorAbsoluto() {
		return leucocitosValorAbsoluto;
	}

	public void setLeucocitosValorAbsoluto(int leucocitosValorAbsoluto) {
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

	public int getBastonetesPercentual() {
		return bastonetesPercentual;
	}

	public void setBastonetesPercentual(int bastonetesPercentual) {
		this.bastonetesPercentual = bastonetesPercentual;
	}

	public int getBastonetesValorAbsoluto() {
		return bastonetesValorAbsoluto;
	}

	public void setBastonetesValorAbsoluto(int bastonetesValorAbsoluto) {
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

	public int getSegmentadosPercentual() {
		return segmentadosPercentual;
	}

	public void setSegmentadosPercentual(int segmentadosPercentual) {
		this.segmentadosPercentual = segmentadosPercentual;
	}

	public int getSegmentadosValorAbsoluto() {
		return segmentadosValorAbsoluto;
	}

	public void setSegmentadosValorAbsoluto(int segmentadosValorAbsoluto) {
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

	public int getEosinofilosPercentual() {
		return eosinofilosPercentual;
	}

	public void setEosinofilosPercentual(int eosinofilosPercentual) {
		this.eosinofilosPercentual = eosinofilosPercentual;
	}

	public int getEosinofilosValorAbsoluto() {
		return eosinofilosValorAbsoluto;
	}

	public void setEosinofilosValorAbsoluto(int eosinofilosValorAbsoluto) {
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

	public int getMonocitosPercentual() {
		return monocitosPercentual;
	}

	public void setMonocitosPercentual(int monocitosPercentual) {
		this.monocitosPercentual = monocitosPercentual;
	}

	public int getMonocitosValorAbsoluto() {
		return monocitosValorAbsoluto;
	}

	public void setMonocitosValorAbsoluto(int monocitosValorAbsoluto) {
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

	public int getLinfocitosPercentual() {
		return linfocitosPercentual;
	}

	public void setLinfocitosPercentual(int linfocitosPercentual) {
		this.linfocitosPercentual = linfocitosPercentual;
	}

	public int getLinfocitosValorAbsoluto() {
		return linfocitosValorAbsoluto;
	}

	public void setLinfocitosValorAbsoluto(int linfocitosValorAbsoluto) {
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

	public int getRdwValorEncontrado() {
		return rdwValorEncontrado;
	}

	public void setRdwValorEncontrado(int rdwValorEncontrado) {
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
