package system.banco.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import system.banco.dto.autenticacao.Signup;
import system.banco.models.Usuario;
import system.banco.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	private final Logger LOG = LoggerFactory.getLogger(Usuario.class);

	@Autowired
	private UsuarioRepository usuarioRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;

	public Usuario createUsuario(Signup cadastro) {
		Usuario usuario = new Usuario(cadastro.getEmail(), 0L, bCryptEncoder.encode(cadastro.getSenha()));
		usuarioRepo.save(usuario);
		LOG.info("Usuario " + usuario.getId() + " criado com sucesso!");
		return usuario;
	}

	public Usuario readUsuario(Long id) {
		return usuarioRepo.findById(id).get();
	}
	
	public void updateUsuario(Usuario usuario) {
		usuarioRepo.save(usuario);
		LOG.info("Usuario " + usuario.getId() + " alterado com sucesso!");
	}
	
	public void deleteUsuario(Long id) {
		usuarioRepo.deleteById(id);
		LOG.info("Usuario " + id + " deletado com sucesso!");
	}
	
	//Other function
	public List<Usuario> readAll(){
		return usuarioRepo.findAll();
	}
	
	//Buscar usuário pelo email - New
	public Usuario readByEmail(String email) {
		return usuarioRepo.findByEmail(email);
	}
}
