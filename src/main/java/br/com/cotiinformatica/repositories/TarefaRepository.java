package br.com.cotiinformatica.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.PrioridadeTarefa;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.ITarefaRepository;

public class TarefaRepository implements ITarefaRepository {

	// componente do Spring para implementarmos as rotinas do JDBC
	private JdbcTemplate jdbcTemplate;

	// método construtor para inicializar o JdbcTemplate
	// Ele deverá ser inicializado com o DATA SOURCE (conexão) de banco de dados
	public TarefaRepository(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Tarefa obj) throws Exception {

		String query = "insert into tarefa(nome, data, hora, descricao, prioridade, idusuario) values(?, ?, ?, ?, ?, ?)";

		Object[] params = {
			obj.getNome(),
			DateHelper.toString(obj.getData()),
			obj.getHora(),
			obj.getDescricao(),
			obj.getPrioridade().toString(),
			obj.getUsuario().getIdUsuario()
		};
		
		jdbcTemplate.update(query, params);
	}

	@Override
	public void update(Tarefa obj) throws Exception {

		String query = "update tarefa set nome=?, data=?, hora=?, descricao=?, prioridade=? where idtarefa=? and idusuario=?";
		
		Object[] params = {
				obj.getNome(),
				DateHelper.toString(obj.getData()),
				obj.getHora(),
				obj.getDescricao(),
				obj.getPrioridade().toString(),
				obj.getIdTarefa(),
				obj.getUsuario().getIdUsuario()
			};
		
		jdbcTemplate.update(query, params);
	}

	@Override
	public void delete(Tarefa obj) throws Exception {

		String query = "delete from tarefa where idtarefa=? and idusuario=?";

		Object[] params = {
				obj.getIdTarefa(),
				obj.getUsuario().getIdUsuario()
			};
		
		jdbcTemplate.update(query, params);
	}

	@Override
	public List<Tarefa> getAll() throws Exception {

		String query = "select * from tarefa order by data desc";

		List<Tarefa> lista = jdbcTemplate.query(query, new RowMapper<Tarefa>() {

			@Override
			public Tarefa mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getTarefa(rs);
			}			
		});
		
		return lista;
	}

	@Override
	public Tarefa getById(Integer id) throws Exception {

		String query = "select * from tarefa where idtarefa = ?";
		
		Object[] params = { id };

		List<Tarefa> lista = jdbcTemplate.query(query, params, new RowMapper<Tarefa>() {

			@Override
			public Tarefa mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getTarefa(rs);
			}			
		});
		
		if(lista != null && lista.size() == 1) 
			return lista.get(0); //retornar o primeiro elemento da lista
		else
			return null; //retornar valor vazio
	}

	@Override
	public List<Tarefa> getByDatas(Date dataMin, Date dataMax, Integer idUsuario) throws Exception {

		String query = "select * from tarefa where data between ? and ? and idusuario = ? order by data desc";
		
		Object[] params = { 
				DateHelper.toString(dataMin), 
				DateHelper.toString(dataMax),
				idUsuario
			};

		List<Tarefa> lista = jdbcTemplate.query(query, params, new RowMapper<Tarefa>() {

			@Override
			public Tarefa mapRow(ResultSet rs, int rowNum) throws SQLException {
				return getTarefa(rs);
			}			
		});
		
		return lista;
	}
	
	//método para ler os dados de uma tarefa obtida no banco de dados
	private Tarefa getTarefa(ResultSet rs) throws SQLException {
		
		//ler cada campo da tabela obtido na consulta
		Tarefa tarefa = new Tarefa();
		tarefa.setUsuario(new Usuario());
		
		tarefa.setIdTarefa(rs.getInt("idtarefa"));
		tarefa.setNome(rs.getString("nome"));
		tarefa.setData(rs.getDate("data"));
		tarefa.setHora(rs.getString("hora"));
		tarefa.setDescricao(rs.getString("descricao"));
		tarefa.setPrioridade(PrioridadeTarefa.valueOf(rs.getString("prioridade")));
		tarefa.getUsuario().setIdUsuario(rs.getInt("idusuario"));
		
		return tarefa;		
	}	

}
