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
	 * Método que configura o local do projeto onde estão
	 * as páginas web do sistema (páginas .jsp)
	 */
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		return resolver;
	}
	
	/*
	 * Método que configura a pasta onde deverão ficar
	 * os arquivos de extensão CSS, JS, FONTS ou IMAGENS
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	/*
	 * Método que configura a conexão de banco de dados
	 * utilizada pelo projeto (DATA SOURCE) que será utilizada
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
	 * Método para configurar a interface/classe 'TarefaRepository'
	 * dessa forma o spring será capaz de inicializar este repositorio
	 */
	@Bean
	public ITarefaRepository getTarefaRepository() {
		return new TarefaRepository(getDataSource());
	}
	
	/*
	 * Método para configurar a interface/classe 'UsuarioRepository'
	 * dessa forma o spring será capaz de inicializar este repositorio
	 */
	@Bean
	public IUsuarioRepository getUsuarioRepository() {
		return new UsuarioRepository(getDataSource());
	}
}







