package system.banco.security.JWT;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	private final Logger LOG = LoggerFactory.getLogger(JWTUtil.class);
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private Long expiration;
	
	/**Metodo de geração do token
	 * reponsável por gerar um token a partir do email do usuario
	 * */
	public String generateToke(String email) {
		return Jwts.builder()
				.setSubject(email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
	
	/**Verifica se o token é valido
	 * Em caso de sucesso nas verificações
	 * É retonado true
	 * */
	public boolean tokenValid(String token) {
		Claims claims = getClaims(token);
		
		if(claims != null) {
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		
		
		return false;	
	}
	
	/**Retorna o nome de usuário de um user a partir do token
	 * Em caso de falha é tornado nulo
	 * */
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		
		if(claims != null) {
			return claims.getSubject();
		} 
		
		LOG.warn("Claims nulo");
		return null;
	}
	
	/**Retorna as informações do usuário
	 * Seu nome, Seu token, Suas permissoes e Tempo de expiração
	 * */
	private Claims getClaims(String token) {
		token = token.substring(7);
		try {
			return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e){
			LOG.warn("JWTUtil !!!: Erro ao obter Claims do token: " + e.getMessage());
			return null;
		}
	}
}
