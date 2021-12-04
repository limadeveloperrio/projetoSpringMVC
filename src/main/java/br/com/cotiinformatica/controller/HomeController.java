package br.com.cotiinformatica.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.entities.Tarefa;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.enums.PrioridadeTarefa;
import br.com.cotiinformatica.interfaces.ITarefaRepository;

@Controller
public class HomeController {
	
	@Autowired //inicialização automática
	private ITarefaRepository tarefaRepository;

	@RequestMapping(value = "/home") //URL
	public ModelAndView home(HttpServletRequest request) throws IOException {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		try {
			
			//capturar o usuario autenticado
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//capturando a data atual do sistema
			Date dataAtual = new Date();
			
			//consultar as tarefas do usuario para a data atual
			List<Tarefa> tarefas = tarefaRepository.getByDatas(dataAtual, dataAtual, usuario.getIdUsuario());
			
			//calculando a quantidade de tarefas com prioridade alta, media ou baixa
			int qtdPrioridadeBaixa = 0;
			int qtdPrioridadeMedia = 0;
			int qtdPrioridadeAlta = 0;
			
			for(Tarefa item : tarefas) {
				if(item.getPrioridade() == PrioridadeTarefa.BAIXA) qtdPrioridadeBaixa++;
				else if(item.getPrioridade() == PrioridadeTarefa.MEDIA) qtdPrioridadeMedia++;
				else if(item.getPrioridade() == PrioridadeTarefa.ALTA) qtdPrioridadeAlta++;
			}
			
			//disponibilizar todas as informações para a página
			modelAndView.addObject("dataatual", dataAtual);
			modelAndView.addObject("tarefas", tarefas);
			modelAndView.addObject("qtdprioridadebaixa", qtdPrioridadeBaixa);
			modelAndView.addObject("qtdprioridademedia", qtdPrioridadeMedia);
			modelAndView.addObject("qtdprioridadealta", qtdPrioridadeAlta);
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		return modelAndView;
	}
}






