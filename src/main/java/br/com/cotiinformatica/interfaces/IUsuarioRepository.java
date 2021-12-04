package br.com.cotiinformatica.interfaces;

import br.com.cotiinformatica.entities.Usuario;

public interface IUsuarioRepository extends IBaseRepository<Usuario, Integer> {

	// Método para consultar 1 usuario atraves do email
	Usuario get(String email) throws Exception;

	// Método para consultar 1 usuario atraves do email e senha
	Usuario get(String email, String senha) throws Exception;
}
