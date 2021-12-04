package br.com.cotiinformatica.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.TarefaCadastroDTO;
import br.com.cotiinformatica.dtos.TarefaConsultaDTO;
import br.com.cotiinformatica.dtos.TarefaEdicaoDTO;
import br.com.cotiinformatica.dtos.TarefaRelatorioDTO;
import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.PrioridadeTarefa;
import br.com.cotiinformatica.helpers.DateHelper;
import br.com.cotiinformatica.interfaces.ITarefaRepository;
import br.com.cotiinformatica.reports.TarefaReport;

@Controller // qualifica a classe como um Controlador MVC
public class TarefaController {
	
	@Autowired //inicializar automaticamente
	private ITarefaRepository tarefaRepository;

	@RequestMapping(value = "/cadastro-tarefa") // caminho da página
	public ModelAndView cadastro() throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("cadastro-tarefa");
		modelAndView.addObject("dto", new TarefaCadastroDTO());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/cadastrar-tarefa", method = RequestMethod.POST) // submit do formulário
	public ModelAndView cadastrarTarefa(TarefaCadastroDTO dto, HttpServletRequest request) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("cadastro-tarefa");
		modelAndView.addObject("dto", new TarefaCadastroDTO());
		
		try {
			
			//capturando o usuario autenticação na aplicação
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//preenchendo os dados da entidade
			Tarefa tarefa = new Tarefa();
			
			tarefa.setNome(dto.getNome());
			tarefa.setData(DateHelper.parseDate(dto.getData()));
			tarefa.setHora(dto.getHora());
			tarefa.setDescricao(dto.getDescricao());
			tarefa.setPrioridade(PrioridadeTarefa.valueOf(dto.getPrioridade()));
			tarefa.setUsuario(usuario); //associando a tarefa com usuario
			
			tarefaRepository.create(tarefa);
			
			modelAndView.addObject("mensagem_sucesso", "Tarefa cadastrado com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}	

	@RequestMapping(value = "/consulta-tarefa") // caminho da página
	public ModelAndView consulta() throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("consulta-tarefa");
		modelAndView.addObject("dto", new TarefaConsultaDTO());
		return modelAndView;
	}
	
	@RequestMapping(value = "/consultar-tarefas", method = RequestMethod.POST) // caminho da página
	public ModelAndView consultarTarefas(TarefaConsultaDTO dto, HttpServletRequest request) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("consulta-tarefa");
		
		try {
			//Converter as datas do formato string para o formato java.util.Date
			Date dataMin = DateHelper.parseDate(dto.getDataMin());	
			Date dataMax = DateHelper.parseDate(dto.getDataMax());
			
			//capturando o usuario autenticação na aplicação
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//consultar as tarefas no repositorio atraves das datas
			modelAndView.addObject("tarefas", tarefaRepository.getByDatas(dataMin, dataMax, usuario.getIdUsuario()));
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", dto);
		return modelAndView;
	}

	@RequestMapping(value = "/edicao-tarefa") // caminho da página
	public ModelAndView edicao() throws IOException {
		return new ModelAndView("edicao-tarefa");
	}

	@RequestMapping(value = "/relatorio-tarefa") // caminho da página
	public ModelAndView relatorio() throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("relatorio-tarefa");
		modelAndView.addObject("dto", new TarefaConsultaDTO());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/relatorio-tarefas") // caminho da página
	public ModelAndView relatorioTarefas(TarefaConsultaDTO dto, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("relatorio-tarefa");
		modelAndView.addObject("dto", new TarefaConsultaDTO());
		
		try {
			
			Date dataMin = DateHelper.parseDate(dto.getDataMin());
			Date dataMax = DateHelper.parseDate(dto.getDataMax());
			
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			TarefaRelatorioDTO relatorioDTO = new TarefaRelatorioDTO();
			
			relatorioDTO.setUsuario(usuario);
			relatorioDTO.setDataGeracao(new Date());
			relatorioDTO.setDataMin(dataMin);
			relatorioDTO.setDataMax(dataMax);
			relatorioDTO.setTarefas(tarefaRepository.getByDatas(dataMin, dataMax, usuario.getIdUsuario()));
			
			TarefaReport report = new TarefaReport();
			ByteArrayInputStream stream = report.createPdf(relatorioDTO);
			
			//DOWNLOAD
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition", "attachment; filename=tarefas.pdf");
			
			byte[] dados = stream.readAllBytes();
			
			OutputStream out = response.getOutputStream();
			out.write(dados, 0, dados.length);
			out.flush();
			out.close();
			
			response.getOutputStream().flush();
			
			return null;
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/edicao-tarefas")
	public ModelAndView edicao(int id, HttpServletRequest request) {
		
		//abrir a página de edição (nome da página JSP)
		ModelAndView modelAndView = new ModelAndView("edicao-tarefa");
		
		//criando um objeto da classe DTO
		TarefaEdicaoDTO dto = new TarefaEdicaoDTO();
		
		try {
			
			//capturar os dados do usuario autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//consultar a tarefa no banco de dados atraves do ID..
			Tarefa tarefa = tarefaRepository.getById(id);
			
			//verificar se a tarefa foi encontrada e se a tarefa pertence ao usuario autenticado
			if(tarefa != null && tarefa.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
				
				//transferir os dados da tarefa para o DTO
				dto.setIdTarefa(tarefa.getIdTarefa());
				dto.setNome(tarefa.getNome());
				dto.setDescricao(tarefa.getDescricao());
				dto.setData(DateHelper.toString(tarefa.getData()));
				dto.setHora(tarefa.getHora());
				dto.setPrioridade(tarefa.getPrioridade().toString());
			}
			else {
				throw new Exception("Tarefa inválida.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", dto);
		return modelAndView;
	}
	
	@RequestMapping(value = "/atualizar-tarefa", method = RequestMethod.POST)
	public ModelAndView atualizarTarefa(TarefaEdicaoDTO dto, HttpServletRequest request) {
		
		//abrir a página de edição (nome da página JSP)
		ModelAndView modelAndView = new ModelAndView("edicao-tarefa");
				
		try {
			
			//capturar os dados do usuario autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//capturando os dados da tarefa
			Tarefa tarefa = new Tarefa();
			
			tarefa.setIdTarefa(dto.getIdTarefa());
			tarefa.setNome(dto.getNome());
			tarefa.setData(DateHelper.parseDate(dto.getData()));
			tarefa.setHora(dto.getHora());
			tarefa.setDescricao(dto.getDescricao());
			tarefa.setPrioridade(PrioridadeTarefa.valueOf(dto.getPrioridade()));
			tarefa.setUsuario(usuario); //associando a tarefa com usuario
			
			//atualizar a tarefa no banco de dados
			tarefaRepository.update(tarefa);
			
			modelAndView.addObject("mensagem_sucesso", "Tarefa atualizada com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", dto);
		return modelAndView;
	}
	
	@RequestMapping(value = "/excluir-tarefa")
	public ModelAndView excluirTarefa(Integer id, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("consulta-tarefa");
		
		try {
			
			//capturar os dados do usuario autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//buscar a tarefa no banco de dados atraves do ID
			Tarefa tarefa = tarefaRepository.getById(id);
			
			//verificar se a tarefa foi encontrada e se pertence ao usuario autenticado
			if(tarefa != null && tarefa.getUsuario().getIdUsuario() == usuario.getIdUsuario()) {
				
				//excluindo a tarefa
				tarefaRepository.delete(tarefa);
				
				modelAndView.addObject("mensagem_sucesso", "Tarefa '" + tarefa.getNome() + "', excluída com sucesso.");
			}
			else {
				throw new Exception("Tarefa inválida");
			}			
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", new TarefaConsultaDTO());
		return modelAndView;
	}
	
}








