package labbios.db;

public class CadastroDeExame {
	
	private int CAD_EXAME_ID;
	private String CAD_EXAME_ABREVIATURA;
	private String CAD_EXAME_NOME;
	private GrupoExame GRUPO_EXAME; //Exceção à nomenclatura
	private MaterialExame MATERIAL_EXAME;
	private TipoLaboratorio TIPO_LABORATORIO;
	private String CAD_EXAME_DIAS_ENTREGA;
	private String CAD_EXAME_COD_SUS;
	private int CAD_EXAME_GRUPO_ETIQUETA;
	private char CAD_EXAME_TIPO_ENTRADA;
	
	public int getCAD_EXAME_ID() {
		return CAD_EXAME_ID;
	}
	public void setCAD_EXAME_ID(int cAD_EXAME_ID) {
		CAD_EXAME_ID = cAD_EXAME_ID;
	}
	public String getCAD_EXAME_ABREVIATURA() {
		return CAD_EXAME_ABREVIATURA;
	}
	public void setCAD_EXAME_ABREVIATURA(String cAD_EXAME_ABREVIATURA) {
		CAD_EXAME_ABREVIATURA = cAD_EXAME_ABREVIATURA;
	}
	public String getCAD_EXAME_NOME() {
		return CAD_EXAME_NOME;
	}
	public void setCAD_EXAME_NOME(String cAD_EXAME_NOME) {
		CAD_EXAME_NOME = cAD_EXAME_NOME;
	}
	public GrupoExame getGRUPO_EXAME() {
		return GRUPO_EXAME;
	}
	public void setGRUPO_EXAME(GrupoExame gRUPO_EXAME) {
		GRUPO_EXAME = gRUPO_EXAME;
	}
	public MaterialExame getMATERIAL_EXAME() {
		return MATERIAL_EXAME;
	}
	public void setMATERIAL_EXAME(MaterialExame mATERIAL_EXAME) {
		MATERIAL_EXAME = mATERIAL_EXAME;
	}
	public TipoLaboratorio getTIPO_LABORATORIO() {
		return TIPO_LABORATORIO;
	}
	public void setTIPO_LABORATORIO(TipoLaboratorio tIPO_LABORATORIO) {
		TIPO_LABORATORIO = tIPO_LABORATORIO;
	}
	public String getCAD_EXAME_DIAS_ENTREGA() {
		return CAD_EXAME_DIAS_ENTREGA;
	}
	public void setCAD_EXAME_DIAS_ENTREGA(String cAD_EXAME_DIAS_ENTREGA) {
		CAD_EXAME_DIAS_ENTREGA = cAD_EXAME_DIAS_ENTREGA;
	}
	public String getCAD_EXAME_COD_SUS() {
		return CAD_EXAME_COD_SUS;
	}
	public void setCAD_EXAME_COD_SUS(String cAD_EXAME_COD_SUS) {
		CAD_EXAME_COD_SUS = cAD_EXAME_COD_SUS;
	}
	public int getCAD_EXAME_GRUPO_ETIQUETA() {
		return CAD_EXAME_GRUPO_ETIQUETA;
	}
	public void setCAD_EXAME_GRUPO_ETIQUETA(int cAD_EXAME_GRUPO_ETIQUETA) {
		CAD_EXAME_GRUPO_ETIQUETA = cAD_EXAME_GRUPO_ETIQUETA;
	}
	public char getCAD_EXAME_TIPO_ENTRADA() {
		return CAD_EXAME_TIPO_ENTRADA;
	}
	public void setCAD_EXAME_TIPO_ENTRADA(char cAD_EXAME_TIPO_ENTRADA) {
		CAD_EXAME_TIPO_ENTRADA = cAD_EXAME_TIPO_ENTRADA;
	}

}
