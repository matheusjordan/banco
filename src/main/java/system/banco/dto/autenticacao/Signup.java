package system.banco.dto.autenticacao;

import javax.validation.constraints.NotEmpty;

public class Signup {
	
	@NotEmpty
	private String email;
	
	@NotEmpty
	private String senha;

	@NotEmpty
	public Signup() {}

	public Signup(String email, String senha) {
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
