package system.banco.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import system.banco.security.JWT.JWTUtil;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	private final Logger LOG = LoggerFactory.getLogger(JWTAuthorizationFilter.class);
	
	private UserDetailsService userDetailsSer;
	private JWTUtil jwtUtil;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsSer) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsSer = userDetailsSer;
	}

	/**Função responsável por inteceptar uma requisição
	 * Ao inteceptar é verificado se o usuário está autenticado
	 * Caso esteja, o usuário será autorizado
	 * */
	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) 
																	throws IOException, ServletException{
		//Captura o valor do cabeçalho authorization
		String authHeader = req.getHeader("Authorization");
		
		//Validação do header		//new
		if(authHeader != null && authHeader.startsWith("Bearer ")) {

			UsernamePasswordAuthenticationToken auth = getAuthentication(authHeader);
			
			if(auth != null) {
				
				//Libera o acesso do usuário após verificar todos os dados
				SecurityContextHolder.getContext().setAuthentication(auth);
			
			} else LOG.warn("JWTAuthorizationFilter !!!: Erro no auth: " + auth);
			
		} //else LOG.warn("JWTAuthorizationFilter !!!: Erro no Header: " + authHeader);
		
		//Caso de tudo certo a requisição segue com um usuário autorizado
		chain.doFilter(req, res);
	}
	
	/**Função repsonsavel por capturar o token
	 * E verifica o usuário e seus privilegios
	 * */
	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if(jwtUtil.tokenValid(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsSer.loadUserByUsername(username);
			
			//Após verificar o usuário é retornado um UserDetails e suas permissoes
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		} 
		LOG.warn("JWTAuthorizationFilter !!!: Token inválido!");
		return null;
	}
}
