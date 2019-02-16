package system.banco.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String email;
	
	@Column(name = "conta_id", nullable = false)
	private Long contaId;
	
	@Column(nullable = false)
	private String senha;

	//Constructs
	public Cliente() {}

	public Cliente(String email, Long contaId, String senha) {
		this.email = email;
		this.contaId = contaId;
		this.senha = senha;
	}

	//Methods
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setContaId(Long contaId) {
		this.contaId = contaId;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
