package system.banco.service.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import system.banco.models.Conta;
import system.banco.service.ContaService;

@Component
public class ContaFactory {
	
	@Autowired
	private ContaService contaSer;

	public Conta conta() {
		return contaSer.create( new Conta(0.0, "", null));
	}
}
