package br.com.cotiinformatica.entities;

import java.util.Date;

import br.com.cotiinformatica.enums.PrioridadeTarefa;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tarefa {

	private Integer idTarefa;
	private String nome;
	private Date data;
	private String hora;
	private String descricao;
	private PrioridadeTarefa prioridade;
	private Usuario usuario;
}
