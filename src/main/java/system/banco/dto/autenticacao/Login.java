package system.banco.dto.autenticacao;

import javax.validation.constraints.NotEmpty;

public class Login {
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String senha;
	
	//Constructs
	public Login() {}

	public Login(String email, String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	//Methods
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
