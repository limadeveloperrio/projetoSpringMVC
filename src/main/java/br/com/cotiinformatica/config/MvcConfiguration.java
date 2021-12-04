package br.com.cotiinformatica.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.cotiinformatica.interfaces.ITarefaRepository;
import br.com.cotiinformatica.interfaces.IUsuarioRepository;
import br.com.cotiinformatica.repositories.TarefaRepository;
import br.com.cotiinformatica.repositories.UsuarioRepository;

@Configuration
@ComponentScan(basePackages="br.com.cotiinformatica")
@EnableWebMvc
public class MvcConfiguration extends WebMvcConfigurerAdapter{

	/*
	 * M�todo que configura o local do projeto onde est�o
	 * as p�ginas web do sistema (p�ginas .jsp)
	 */
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/*
	 * M�todo que configura a pasta onde dever�o ficar
	 * os arquivos de extens�o CSS, JS, FONTS ou IMAGENS
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/*
	 * M�todo que configura a conex�o de banco de dados
	 * utilizada pelo projeto (DATA SOURCE) que ser� utilizada
	 * pelas classes da camada de repositorio
	 */
	@Bean
	public DataSource getDataSource() {
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/bd_projetospringmvc01?useTimezone=true&serverTimezone=UTC&useSSL=false");
		dataSource.setUsername("root");
		dataSource.setPassword("coti");
		
		return dataSource;
	}
	
	/*
	 * M�todo para configurar a interface/classe 'TarefaRepository'
	 * dessa forma o spring ser� capaz de inicializar este repositorio
	 */
	@Bean
	public ITarefaRepository getTarefaRepository() {
		return new TarefaRepository(getDataSource());
	}
	
	/*
	 * M�todo para configurar a interface/classe 'UsuarioRepository'
	 * dessa forma o spring ser� capaz de inicializar este repositorio
	 */
	@Bean
	public IUsuarioRepository getUsuarioRepository() {
		return new UsuarioRepository(getDataSource());
	}
}







