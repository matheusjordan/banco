package system.banco.controllers;

import java.util.List;

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
import system.banco.models.Cliente;
import system.banco.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
	
	@PostMapping("/cadastro")
	public ResponseEntity<Cliente> createCliente(@RequestBody Signup cadastro){
		service.createCliente(cadastro);
		return new ResponseEntity<Cliente>(HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> readCliente(@PathVariable(name = "{id}") Long id){
		Cliente cliente = service.readCliente(id);
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente){
		service.updateCliente(cliente);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Cliente> deleteCliente(@PathVariable(name = "{id}") Long id){
		service.deleteCliente(id);
		return new ResponseEntity<Cliente>(HttpStatus.OK);
	}
	
	//Other EndPoints
	@GetMapping("/")
	public ResponseEntity<List<Cliente>> findAll(){
		List<Cliente> clientes = service.readAll();
		return new ResponseEntity<List<Cliente>>(clientes, HttpStatus.OK);
	}
}
