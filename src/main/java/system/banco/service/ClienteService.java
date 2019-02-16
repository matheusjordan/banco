package system.banco.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.banco.dto.autenticacao.Signup;
import system.banco.models.Cliente;
import system.banco.repositories.ClienteRepository;

@Service
public class ClienteService {
	private final Logger LOG = LoggerFactory.getLogger(Cliente.class);

	@Autowired
	private ClienteRepository clienteRepo;

	public Cliente createCliente(Signup cadastro) {
		Cliente cliente = new Cliente(cadastro.getEmail(), 0L, cadastro.getSenha());
		clienteRepo.save(cliente);
		LOG.info("Cliente " + cliente.getId() + " criado com sucesso!");
		return cliente;
	}

	public Cliente readCliente(Long id) {
		return clienteRepo.findById(id).get();
	}
	
	public void updateCliente(Cliente cliente) {
		clienteRepo.save(cliente);
		LOG.info("Cliente " + cliente.getId() + " alterado com sucesso!");
	}
	
	public void deleteCliente(Long id) {
		clienteRepo.deleteById(id);
		LOG.info("Cliente " + id + " deletado com sucesso!");
	}
	
	//Other function
	public List<Cliente> readAll(){
		return clienteRepo.findAll();
	}
}
