package system.banco.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import system.banco.models.Conta;
import system.banco.repositories.ContaRepository;

@Service
public class ContaService {
	private final Logger LOG = LoggerFactory.getLogger(ContaService.class);
	
	@Autowired
	private ContaRepository contaRepo;
	
	public Conta create(Conta conta) {
		contaRepo.save(conta);
		LOG.info("Conta " + conta.getId() + " criada com sucesso!");
		return conta;
	}
	
	public Conta read(Integer id) {
		return contaRepo.findById(id).get();
	}
	
	public void update(Conta conta) {
		contaRepo.save(conta);
		LOG.info("Conta " + conta.getId() + " alterada com sucesso!");
	}
	
	public void delete(Integer id) {
		contaRepo.deleteById(id);
		LOG.info("Conta " + id + " deletada com sucesso!");
	}
	
	//Other Functions
	public List<Conta> readAll(){
		return contaRepo.findAll();
	}
}
