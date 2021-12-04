package br.com.cotiinformatica.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthorizationFilter implements Filter {

	public AuthorizationFilter() {
	}

	public void destroy() {
	}

	/*
	 * M�todo utilizado para monitorar as requisi��es feitas pelo usuario no projeto web
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//converter os objetos ServletRequest e ServletResponse em HttpServletRequest e HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//definir quais s�o as URLs permitidas no projeto (sem autentica��o)
		List<String> urlsPermitidias = new ArrayList<String>();
		urlsPermitidias.add("/"); //p�gina raiz do projeto
		urlsPermitidias.add("/autenticar-usuario"); //a��o de autentica��o do usuario
		urlsPermitidias.add("/register"); //p�gina de cadastro do usuario
		urlsPermitidias.add("/cadastrar-usuario"); //a��o de cadastro do usuario
		urlsPermitidias.add("/password-recover"); //p�gina de recupera��o de senha
		urlsPermitidias.add("/recuperar-senha"); //a��o de recupera��o de senha
		
		//verificando se a p�gina que o usuario est� tentando acessar n�o � alguma das URLS permitidas
		if(!urlsPermitidias.contains(req.getServletPath())) {
			
			//verificar se o usuario n�o est� autenticado (se n�o h� uma sess�o aberta)
			if(req.getSession().getAttribute("usuario_autenticado") == null) {
				//redirecionar de volta para a p�gina inicial do sistema
				resp.sendRedirect("/projetoSpringMVC01/");
			}			
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
