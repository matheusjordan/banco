package system.banco.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import system.banco.models.Usuario;
import system.banco.service.UsuarioService;

/**Classe por gerenciar regras de usuario
 * 
 * */
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioService usuarioSer;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//Busca um suario pelo nome
		Usuario usuario = usuarioSer.readByEmail(email);
		
		//Verifica se o usuario é nulo
		if(usuario == null) {
			throw new UsernameNotFoundException(null);
		}
		
		//Retona um usuario conforme as especificações Spring
		return new UserDetailsImpl(usuario.getId(), usuario.getEmail(), usuario.getSenha(), usuario.getPerfis());
	}

}
