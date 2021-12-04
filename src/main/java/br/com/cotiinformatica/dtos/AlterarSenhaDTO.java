package br.com.cotiinformatica.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlterarSenhaDTO {

	private String senhaAtual;
	private String novaSenha;
	private String novaSenhaConfirmacao;

}
