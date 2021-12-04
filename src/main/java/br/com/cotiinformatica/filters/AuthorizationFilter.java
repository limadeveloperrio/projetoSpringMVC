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
	 * Método utilizado para monitorar as requisições feitas pelo usuario no projeto web
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		//converter os objetos ServletRequest e ServletResponse em HttpServletRequest e HttpServletResponse
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		//definir quais são as URLs permitidas no projeto (sem autenticação)
		List<String> urlsPermitidias = new ArrayList<String>();
		urlsPermitidias.add("/"); //página raiz do projeto
		urlsPermitidias.add("/autenticar-usuario"); //ação de autenticação do usuario
		urlsPermitidias.add("/register"); //página de cadastro do usuario
		urlsPermitidias.add("/cadastrar-usuario"); //ação de cadastro do usuario
		urlsPermitidias.add("/password-recover"); //página de recuperação de senha
		urlsPermitidias.add("/recuperar-senha"); //ação de recuperação de senha
		
		//verificando se a página que o usuario está tentando acessar não é alguma das URLS permitidas
		if(!urlsPermitidias.contains(req.getServletPath())) {
			
			//verificar se o usuario não está autenticado (se não há uma sessão aberta)
			if(req.getSession().getAttribute("usuario_autenticado") == null) {
				//redirecionar de volta para a página inicial do sistema
				resp.sendRedirect("/projetoSpringMVC01/");
			}			
		}
		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
