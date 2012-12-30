package labbios.menu;
	

import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
  
public class MenuBean {  
    
	public static String CADASTRO_CLIENTE = "goCadCliente";
	public static String CADASTRO_MEDICOS = "goCadMedicos";
	
    public void save() {  
        addMessage("Data saved");  
    }  
      
    public void update() {  
        addMessage("Data updated");  
    }  
      
    public void delete() {  
        addMessage("Data deleted");  
    }  
      
    public void addMessage(String summary) {  
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
    
    public String cadastroCliente(){
    	System.out.println("cadastroCliente");
    	FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().getFlash().setKeepMessages(true);
    	return CADASTRO_CLIENTE;
    }
    
    public String cadastroMedicos(){
    	System.out.println("cadastroMédicos");
    	FacesContext context = FacesContext.getCurrentInstance();
    	context.getExternalContext().getFlash().setKeepMessages(true);
    	return CADASTRO_MEDICOS;
    }
    
    public String teste(){
    	FacesContext context = FacesContext.getCurrentInstance();
    	return "paciente";
    }
}  


