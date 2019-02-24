package system.banco.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import system.banco.security.JWT.JWTUtil;
import system.banco.security.filter.JWTAuthenticationFilter;
import system.banco.security.filter.JWTAuthorizationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	private final String[] PUBLIC_GET_ENDPOINTS = {};
	private final String[] PUBLIC_POST_ENDPOINTS = {"/usuarios/cadastro"};
	
	@Autowired
	UserDetailsService userDetailsSer;
	
	@Autowired
	JWTUtil jwtUtil;
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		
		/**Configuração de Cors
		 * Permite que minha Aplicação possa ser acessada de múltiplas fontes??
		 * Seja através de um celular, pc, aplicativo de teste e etc.
		 * */
		http.cors();
		
		/**Configuração de CSRF
		 * Desabilita a proteção de ataques CSRF
		 * Ataques que utilizam sessões amarzenadas em cache??
		 * */
		http.csrf().disable();
		
		/**Configuração de Autorização de requisições
		 * É definido que  alguns endpoints com o metodo GET
		 * Podem ser acessados sem publicamente/sem autenticação.
		 * */
		http.authorizeRequests().antMatchers(HttpMethod.GET, PUBLIC_GET_ENDPOINTS).permitAll();
		
		//Endpoint post permitido para acesso publico
		http.authorizeRequests().antMatchers(HttpMethod.POST, PUBLIC_POST_ENDPOINTS).permitAll();
		
		/**Configuração de Autorização de requisições
		 * Para cada requisição feita no site
		 * É necessário estar autenticado.
		 * */
		http.authorizeRequests().anyRequest().authenticated();
		
		/**Configuração de filtro
		 * É adicionao um filtro implementado
		 * Neste caso será um filtro que captura requisições de login
		 * */
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		
		/**Configuração de filtro
		 * Filtro responsável por autorizar um usuário
		 * */
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsSer));
		/**Configuração do gerenciamento de sessões
		 * Stateless serve para que as minhas sessões
		 * Não sejam salvas no PC.
		 * */
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	/**Bean de Configuração de cors
	 * Define a configuração dos Cors
	 * Carregando as configurações default dos Cors.
	 * */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	
	/**Bean do Objeto BCrypt
	 * Possui as funcionalidades de um objeto BCrypt
	 * */
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**Classe de configuração do usuario
	 * É identificado o meu userDetailsServiceImpl através do autowired do UserDetailsService
	 * É definido o algoritmo de encriptação da senha
	 * */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth
			.userDetailsService(this.userDetailsSer)
			.passwordEncoder(this.bCryptPasswordEncoder());
	}
}
