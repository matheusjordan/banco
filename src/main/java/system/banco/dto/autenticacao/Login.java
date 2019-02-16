package system.banco.dto.autenticacao;

import javax.validation.constraints.NotEmpty;

public class Login {
	
	@NotEmpty
	private String contaNum;
	
	@NotEmpty
	private String senha;
	
	//Constructs
	public Login() {}

	public Login(String contaNum, String senha) {
		super();
		this.contaNum = contaNum;
		this.senha = senha;
	}

	//Methods
	public String getContaNum() {
		return contaNum;
	}

	public void setContaNum(String contaNum) {
		this.contaNum = contaNum;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
