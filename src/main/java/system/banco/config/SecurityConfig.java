package system.banco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	public final String[] PUBLIC_GET_ENDPOINTS = {"/cliente/**"};

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
		
		/**Configuração de Autorização de requisições
		 * Para cada requisição feita no site
		 * É necessário estar autenticado.
		 * */
//		http.authorizeRequests().anyRequest().authenticated();
		
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
}
