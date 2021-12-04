package br.com.cotiinformatica.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterDTO {

	private String nome;
	private String email;
	private String senha;
	private String senhaConfirmacao;
	
}
