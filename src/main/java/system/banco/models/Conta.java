package system.banco.models;

import java.util.List;
import java.util.Random;

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
	
	@Column(unique = true, name = "carteira_bitcoin", nullable = false)
	private String carteira;
	
	@ManyToMany
	@JoinTable
	private List<Transacao> transacoes;
	
	//Constructs
	public Conta() {}

	public Conta(Double saldo, String carteira, List<Transacao> transacoes) {
		this.saldo = saldo;
		this.carteira = "" + (new Random()).nextInt(1244454845);
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

	public String getCarteira() {
		return carteira;
	}

	public void setCarteira(String carteira) {
		this.carteira = carteira;
	}

	public List<Transacao> getTransacoes() {
		return transacoes;
	}

	public void setTransacoes(List<Transacao> transacoes) {
		this.transacoes = transacoes;
	}
}
