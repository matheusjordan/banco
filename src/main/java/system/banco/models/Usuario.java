package system.banco.models;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import system.banco.enums.Perfil;

@Entity
@Table(name = "tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(name = "conta_id", nullable = false)
	private Long contaId;
	
	@Column(nullable = false)
	private String senha;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "PERFIS")
	private Set<Integer> perfis = new HashSet<>();

	//Constructs
	public Usuario() {
		
		//Todo Usuario por padrão é um cliente
		this.addPerfil(Perfil.CLIENTE);
	}

	public Usuario(String email, Long contaId, String senha) {
		this();
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
	
	//Funções do ENUM PERFIL
	
	//Retorna todos os perfis cadastrados
	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	
	//Adiciona um perfil ao meu cliente
	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCod());
	}
}
