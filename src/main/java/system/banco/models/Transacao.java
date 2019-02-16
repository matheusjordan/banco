package system.banco.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_transacoes")
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Long contaId;
	
	@Column(unique = true, name = "tipo_transacao", nullable = false)
	private Integer tipoTransacao;
	
	//Construct
	public Transacao() {}

	public Transacao(Long contaId, Integer tipoTransacao) {
		this.contaId = contaId;
		this.tipoTransacao = tipoTransacao;
	}
	
	//Methods
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getContaId() {
		return contaId;
	}

	public void setConta(Long contaId) {
		this.contaId = contaId;
	}

	public Integer getTipoTransacao() {
		return tipoTransacao;
	}

	public void setTipoTransacao(Integer tipoTransacao) {
		this.tipoTransacao = tipoTransacao;
	}
}
