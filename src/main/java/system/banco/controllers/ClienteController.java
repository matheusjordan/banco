package system.banco.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import system.banco.dto.autenticacao.Signup;
import system.banco.models.Usuario;
import system.banco.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class ClienteController {

	@Autowired
	private UsuarioService service;
	
	@PostMapping("/cadastro")
	public ResponseEntity<Usuario> createUsuario(@RequestBody @Valid Signup cadastro){
		service.createUsuario(cadastro);
		return new ResponseEntity<Usuario>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> readUsuario(@PathVariable(value = "id") Long id){
		Usuario cliente = service.readUsuario(id);
		return new ResponseEntity<Usuario>(cliente, HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Usuario> updateUsuario(@RequestBody @Valid Usuario usuario){
		service.updateUsuario(usuario);
		return new ResponseEntity<Usuario>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deleteUsuario(@PathVariable(value = "id") Long id){
		service.deleteUsuario(id);
		return new ResponseEntity<Usuario>(HttpStatus.OK);
	}
	
	//Other EndPoints
	@GetMapping("/")
	public ResponseEntity<List<Usuario>> findAll(){
		List<Usuario> usuarios = service.readAll();
		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
}
