package br.com.cotiinformatica.interfaces;

import java.util.Date;
import java.util.List;

import br.com.cotiinformatica.entities.Tarefa;

public interface ITarefaRepository extends IBaseRepository<Tarefa, Integer> {

	List<Tarefa> getByDatas(Date dataMin, Date dataMax, Integer idUsuario) throws Exception;

}
