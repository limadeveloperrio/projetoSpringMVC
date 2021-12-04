package br.com.cotiinformatica.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TarefaEdicaoDTO {

	private Integer idTarefa;
	private String nome;
	private String data;
	private String hora;
	private String descricao;
	private String prioridade;

}
