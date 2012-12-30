package labbios.beans;

import java.util.ArrayList;
import java.util.List;

import labbios.dto.CadastroDeExame;
import labbios.dto.DadosDoExameSuporte;

public class DadosDoExameBean {
	
	private List<DadosDoExameSuporte> listaSuporte;
	
	public DadosDoExameBean()
	{
//		CadastroDeExame exame1 = new CadastroDeExame();
//		CadastroDeExame exame2 = new CadastroDeExame();
//		CadastroDeExame exame3 = new CadastroDeExame();
//		CadastroDeExame exame4 = new CadastroDeExame();
//		CadastroDeExame exame5 = new CadastroDeExame();
//		
//		DadosDoExameSuporte suporte1 = new DadosDoExameSuporte(exame1, "Plaquetas", "mg", 30);
//		DadosDoExameSuporte suporte2 = new DadosDoExameSuporte(exame2, "Globulos Brancos", "mg", 35);
//		DadosDoExameSuporte suporte3 = new DadosDoExameSuporte(exame3, "Globulos Vermelhos", "mg", 40);
//		DadosDoExameSuporte suporte4 = new DadosDoExameSuporte(exame4, "Protrombina", "mg", 45);
//		DadosDoExameSuporte suporte5 = new DadosDoExameSuporte(exame5, "Hemaceas", "mg", 50);
//			
//		listaSuporte = new ArrayList<DadosDoExameSuporte>();
//		listaSuporte.add(suporte1);
//		listaSuporte.add(suporte2);
//		listaSuporte.add(suporte3);
//		listaSuporte.add(suporte4);
//		listaSuporte.add(suporte5);
	}

	public List<DadosDoExameSuporte> getListaSuporte() {
		CadastroDeExame exame1 = new CadastroDeExame();
		CadastroDeExame exame2 = new CadastroDeExame();
		CadastroDeExame exame3 = new CadastroDeExame();
		CadastroDeExame exame4 = new CadastroDeExame();
		CadastroDeExame exame5 = new CadastroDeExame();
		
		DadosDoExameSuporte suporte1 = new DadosDoExameSuporte(exame1, "Plaquetas", "mg", 30);
		DadosDoExameSuporte suporte2 = new DadosDoExameSuporte(exame2, "Globulos Brancos", "mg", 35);
		DadosDoExameSuporte suporte3 = new DadosDoExameSuporte(exame3, "Globulos Vermelhos", "mg", 40);
		DadosDoExameSuporte suporte4 = new DadosDoExameSuporte(exame4, "Protrombina", "mg", 45);
		DadosDoExameSuporte suporte5 = new DadosDoExameSuporte(exame5, "Hemaceas", "mg", 50);
			
		listaSuporte = new ArrayList<DadosDoExameSuporte>();
		listaSuporte.add(suporte1);
		listaSuporte.add(suporte2);
		listaSuporte.add(suporte3);
		listaSuporte.add(suporte4);
		listaSuporte.add(suporte5);
		
		
		return listaSuporte;
	}

	public void setListaSuporte(List<DadosDoExameSuporte> listaSuporte) {
		this.listaSuporte = listaSuporte;
	}
	
	

}
