package br.com.cotiinformatica.dtos;

import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.entities.Usuario;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TarefaRelatorioDTO {

	private Usuario usuario; // usu�rio autenticado
	private Date dataGeracao; // data de gera��o do relat�rio

	private Date dataMin; // data inicio do filtro de pesquisa
	private Date dataMax; // data fim do filtro de pesquisa

	private List<Tarefa> tarefas; // listagem de tarefas
}
