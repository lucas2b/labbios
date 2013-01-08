package labbios.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterLogin implements Filter {
	
	FilterConfig filter;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
	
	public String retornaPagina(){
		return "";
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String url = req.getRequestURL().toString();
		System.out.println("##URL = "+url);
		
		Object logged = req.getSession().getAttribute("userlogged");
		System.out.println("## logged = "+logged);
		
		if(logged == null  || ((Boolean)logged).booleanValue() == false){
			//Permite Login
			if(!url.contains("login")){
				resp.sendRedirect("loginBios.xhtml");
			}
		}
		
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*if(url.contains("index"))
		{	
			chain.doFilter(request, response);
		}
		else			
		{
			
			if(logged == null)
			{
				System.out.println("##Login Nulo");
				resp.sendRedirect("/labbios/loginBios.xhtml");				
			}
			else
			{
				chain.doFilter(request, response);
			}*/
		
	}

	@Override
	public void init(FilterConfig filter) throws ServletException {
		this.filter = filter;
		
	}

}
