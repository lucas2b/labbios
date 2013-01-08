package labbios.beans;

import java.io.IOException;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import labbios.dao.LoginDAO;
import labbios.dto.Login;

public class LoginBean {
	
	private String nome;
	private String senha;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	LoginDAO loginDAO = new LoginDAO();
	
	public String adicionarUsuario() throws ClassNotFoundException, SQLException
	{
		Login login = new Login();
		login.setLOGIN_NOME(nome);
		login.setLOGIN_SENHA(senha);
		
		loginDAO.adicionarUsuario(login);
		return "usuarioAdicionadoComSucesso";	
	}
	
	public String validarLogin() throws ClassNotFoundException, SQLException
	{
		Login login = new Login();
		login.setLOGIN_NOME(nome);
		login.setLOGIN_SENHA(senha);
		
		if(loginDAO.validarLogin(login))
		{
			System.out.println("## ACESSO LIBERADO");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userlogged", true);
			return "acessoPermitido";
		}
		else
		{
			System.out.println("## ACESSO NEGADO");
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userlogged", false);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"", "Verifique suas credenciais!"));  
			return null;
			//return "acessoNegado";
		}
	}
	
	//TODO - Não está ainda pronto
	public void deslogar(){
		System.out.println("DESLOGAR");
		/*FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userlogged", false);
		Map sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();  
        sessionMap.clear();  
		*/
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
		FacesContext facesContext = FacesContext.getCurrentInstance();  
	    HttpSession session = (HttpSession) facesContext .getExternalContext().getSession(false);  
	    session.invalidate();
		try {
			response.sendRedirect("/loginBios.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return "returnLogin";
	}
	
	public String redirecionaInicio(){
		return "returnLogin";
	}


}
