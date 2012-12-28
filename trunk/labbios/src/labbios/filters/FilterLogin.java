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
	private boolean logado;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		String url = req.getRequestURL().toString();
		
		Object logged = req.getSession().getAttribute("userlogged");
		
		
		if(url.contains("validarUsuario"))
		{	
			chain.doFilter(request, response);
		}
		else			
		{
			
			if(logged == null)
			{
				resp.sendRedirect("/labbios/faces/validarUsuario.jsp");				
			}
			else
			{
				chain.doFilter(request, response);
			}
		}

		
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
