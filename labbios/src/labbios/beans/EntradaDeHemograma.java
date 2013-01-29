package labbios.beans;

import java.util.List;

import labbios.dto.Exame;
import labbios.dto.Resultado;

public class EntradaDeHemograma {
	
	private Exame exameSelecionado;
	private List<Resultado> listaDeExibicao;
	
	//CAMPOS DO LEUCOGRAMA
	
	private int leucocitos; //Segmento absoluto 
	private int bastonetes; //percentual sobre leucocitos
	private int segmentados; //percentual sobre leucocitos
	private int eosinofilos; //percentual sobre leucocitos
	private int monocitos; //percentual sobre leucocitos
	private int linfocitos; //percentual sobre leucocitos
		
	//CAMPOS DO ERITROGRAMA
		
	private int eritrocitos;
	private int hemoglobina;
	private int hematocrito;
	private int mcv;
	private int mch;
	private int mchc;
	private int rdw;
	
	
	//Construtor para resultado existente
	public EntradaDeHemograma(Exame exameSelecionado, List<Resultado> listaDeExibicao)
	{
		this.exameSelecionado = exameSelecionado;
		this.listaDeExibicao = listaDeExibicao;
	}
	
	//Construtor para novo resultado
	public EntradaDeHemograma(Exame exameSelecionado)
	{
		this.exameSelecionado = exameSelecionado;
	}

}
