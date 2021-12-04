package br.com.cotiinformatica.controller;

import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.cotiinformatica.dtos.AlterarSenhaDTO;
import br.com.cotiinformatica.dtos.LoginDTO;
import br.com.cotiinformatica.dtos.PasswordRecoverDTO;
import br.com.cotiinformatica.dtos.RegisterDTO;
import br.com.cotiinformatica.entities.Usuario;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.messages.MailMessage;

@Controller // define a classe como um controlador do spring
public class UsuarioController {
	
	@Autowired //inicialização automática do atributo
	private IUsuarioRepository usuarioRepository;

	@RequestMapping(value = "/") // URL
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView("login"); // página .jsp	
		modelAndView.addObject("dto", new LoginDTO());
		return modelAndView;
	}
	
	//método para capturar os dados enviados pelo formulário (SUBMIT)
	@RequestMapping(value = "/autenticar-usuario", method = RequestMethod.POST)
	public ModelAndView autenticarUsuario(LoginDTO dto, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("login"); // página .jsp	
		
		try {
			
			Usuario usuario = usuarioRepository.get(dto.getEmail(), dto.getSenha());
			
			//verificar se o usuario não foi encontrado..
			if(usuario == null) {
				throw new Exception("Acesso negado. Usuário inválido.");
			}
			
			//gravar os dados do usuario em sessão..
			request.getSession().setAttribute("usuario_autenticado", usuario);			
			modelAndView.setViewName("redirect:home"); //redirecionamento
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", dto);
		return modelAndView;
	}

	@RequestMapping(value = "/register") // URL
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView("register"); // página .jsp
		modelAndView.addObject("dto", new RegisterDTO());
		return modelAndView;
	}
	
	//método para capturar os dados enviados pelo formulário (SUBMIT)
	@RequestMapping(value = "/cadastrar-usuario", method = RequestMethod.POST)
	public ModelAndView cadastrarUsuario(RegisterDTO dto) {
		ModelAndView modelAndView = new ModelAndView("register"); // página .jsp
		
		try {
			
			//verificar se as senhas não estão iguais
			if(!dto.getSenha().equals(dto.getSenhaConfirmacao())) {
				throw new Exception("Senhas não conferem.");
			}
			
			//verificar se o email informado já esta cadastrado no banco de dados
			if(usuarioRepository.get(dto.getEmail()) != null) {
				throw new Exception("O email informado já está cadastrado, tente outro.");
			}
			
			//capturando os dados enviados pelo DTO..
			Usuario usuario = new Usuario();
			
			usuario.setNome(dto.getNome());
			usuario.setEmail(dto.getEmail());
			usuario.setSenha(dto.getSenha());
			
			//cadastrar o usuario no banco de dados..
			usuarioRepository.create(usuario);
			
			modelAndView.addObject("mensagem_sucesso", "Sua conta de usuário foi criada com sucesso!");
			dto = new RegisterDTO(); //limpar os campos do formulário
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", dto);
		return modelAndView;
	}

	@RequestMapping(value = "/password-recover") // URL
	public ModelAndView passwordRecover() {
		ModelAndView modelAndView = new ModelAndView("password-recover"); // página .jsp
		modelAndView.addObject("dto", new PasswordRecoverDTO());
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/recuperar-senha", method = RequestMethod.POST) 
	public ModelAndView recuperarSenha(PasswordRecoverDTO dto) {
		
		ModelAndView modelAndView = new ModelAndView("password-recover");
		
		try {
		
			//consultar o usuario no banco de dados atraves do email
			Usuario usuario = usuarioRepository.get(dto.getEmail());
			
			//verificar se o usuario foi encontrado
			if(usuario != null) {
				
				//gerando uma nova senha para o usuario
				usuario.setSenha(String.valueOf(new Random().nextInt(999999999)));
				
				//enviando a senha para o email do usuario
				MailMessage.send(
						usuario.getEmail(), 
						"Nova senha gerada com sucesso - COTI Informatica Controle de Tarefas", 
						"Olá, " + usuario.getNome() +
						"\n\nSua nova senha foi gerada com sucesso: " + usuario.getSenha() +
						"\nUtilize esta senha para acessar o sistema." + 
						"\n\nAtt\nEquipe COTI Informática.");
				
				//alterando a senha no banco de dados
				usuarioRepository.update(usuario);
				
				modelAndView.addObject("mensagem_sucesso", "Nova senha gerada com sucesso. Acesse sua conta de email.");
			}
			else {
				throw new Exception("O email informado é inválido. Usuáro não encontrado.");
			}
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", new PasswordRecoverDTO());		
		return modelAndView;
	}
	
	@RequestMapping(value = "/logout") //URL
	public ModelAndView logout(HttpServletRequest request) {
		
		//apagar os dados gravados na sessão
		request.getSession().removeAttribute("usuario_autenticado");
		
		//redirecionando para a página inicial do sistema
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/"); //redirecionamento
		
		return modelAndView;
	}
	
	//página para exibir os dados do usuario autenticado (minha conta)
	@RequestMapping(value = "/userdata")
	public ModelAndView userdata() {
		
		ModelAndView modelAndView = new ModelAndView("userdata");
		return modelAndView;
	}
	
	//página para alteração da senha do usuário
	@RequestMapping(value = "/alterar-senha")
	public ModelAndView alterarSenha() {
		
		ModelAndView modelAndView = new ModelAndView("alterar-senha");
		modelAndView.addObject("dto", new AlterarSenhaDTO());
		
		return modelAndView;
	}
	
	//método para processar o SUBMIT do formulario e fazer
	//a atualização da senha do usuario
	@RequestMapping(value = "atualizar-senha", method = RequestMethod.POST)
	public ModelAndView atualizarSenha(AlterarSenhaDTO dto, HttpServletRequest request) {
		
		ModelAndView modelAndView = new ModelAndView("alterar-senha");
		
		try {
			
			//capturar o usuario autenticado no sistema
			Usuario usuario = (Usuario) request.getSession().getAttribute("usuario_autenticado");
			
			//verificar se a confirmação da nova senha esta errada
			if( ! dto.getNovaSenha().equals(dto.getNovaSenhaConfirmacao()))
				throw new Exception("Senhas não conferem, por favor verifique.");
			
			//verificar se a senha atual esta errada
			if(usuarioRepository.get(usuario.getEmail(), dto.getSenhaAtual()) == null)
				throw new Exception("Senha atual inválida, por favor verifique.");
			
			//atualizar a senha do usuario
			usuario.setSenha(dto.getNovaSenha());
			usuarioRepository.update(usuario);
			
			modelAndView.addObject("mensagem_sucesso", "Senha de acesso atualizada com sucesso.");
		}
		catch(Exception e) {
			modelAndView.addObject("mensagem_erro", e.getMessage());
		}
		
		modelAndView.addObject("dto", new AlterarSenhaDTO());		
		return modelAndView;
		
	}
}





