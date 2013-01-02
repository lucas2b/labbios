package labbios.beans;

import java.sql.SQLException;

import javax.faces.context.FacesContext;

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
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userlogged", true);
			return "acessoPermitido";
		}
		else
		{
			return "acessoNegado";
		}
	}


}
