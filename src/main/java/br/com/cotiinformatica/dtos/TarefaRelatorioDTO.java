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

	private Usuario usuario; // usuário autenticado
	private Date dataGeracao; // data de geração do relatório

	private Date dataMin; // data inicio do filtro de pesquisa
	private Date dataMax; // data fim do filtro de pesquisa

	private List<Tarefa> tarefas; // listagem de tarefas
}
