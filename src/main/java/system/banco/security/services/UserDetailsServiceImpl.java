package system.banco.security.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import system.banco.models.Usuario;
import system.banco.service.UsuarioService;

/**Classe por gerenciar regras de usuario
 * 
 * */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private final Logger LOG = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
			
	@Autowired
	private UsuarioService usuarioSer;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//Busca um suario pelo nome
		Usuario usuario = usuarioSer.readByEmail(email);
		
		//Verifica se o usuario é nulo
		if(usuario == null) {
			LOG.warn("UserDetailsServiceImpl !!!: usuário nulo");
			throw new UsernameNotFoundException(null);
		}
		
		//Retona um usuario conforme as especificações Spring
		return new UserDetailsImpl(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}

}
