package system.banco.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_contas")
public class Conta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double saldo;
	
	@Column(unique = true, name = "num_conta", nullable = false)
	private Integer numConta;
	
	@Column(name="tipo_conta", nullable = false)
	private Integer tipoConta;
	
	@ManyToMany
	@JoinTable
	private List<Transacao> transacoes;
	
	//Constructs
	public Conta() {}

	public Conta(Double saldo, Integer numConta, Integer tipoConta, List<Transacao> transacoes) {
		super();
		this.saldo = saldo;
		this.numConta = numConta;
		this.tipoConta = tipoConta;
		this.transacoes = transacoes;
	}

	//Methods
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
}
