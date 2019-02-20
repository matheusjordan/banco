package system.banco.security.JWT.filters;

import java.util.ArrayList;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import system.banco.dto.autenticacao.Login;
import system.banco.security.JWT.JWTUtil;
import system.banco.security.services.UserDetailsImpl;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private JWTUtil jwtUtil;
	private AuthenticationManager authenticationManager;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}
	
	/**Função responsável por verificar os dados de login
	 * Caso os dados estiverem corretos é retornado um objeto Authentication
	 * Objeto este que permite que meu usuario possa ser authentyicado
	 * */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
		
		try {
			//Tenta converter os dados da minha requisição para um objeto do tipo login
			Login login = new ObjectMapper().readValue(req.getInputStream(), Login.class);
			
			//Gera um token do SpringSecurity do meu usuário
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(login.getEmail(), login.getSenha(), new ArrayList<>()); 
		
			//autentica/valida o token gerado acima
			Authentication auth = authenticationManager.authenticate(authToken);
			
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) 
																			throws IOException, ServletException{
		//É obtido através do objeto auth as informações necessárias para construir meu objeto UserDetails
		UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();
		
		//Obtem o nome de usuário do login
		String username = userDetails.getUsername();
		
		//É gerado um token para o meu usuário autenticado
		String token = jwtUtil.generateToke(username);
		
		//É acrescentado ao Header do meu response o token do meu usuário
		res.addHeader("Authorization", "Bearer " + token);
	}
}
