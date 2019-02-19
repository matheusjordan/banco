package system.banco.enums;

public enum Perfil {
	
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE(2, "ROLE_CLIENTE");
		
	private Integer cod;
	private String descricao;
	
	private Perfil(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public Integer getCod() {
		return this.cod;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod != null && cod > 0) {
			
			for(Perfil perfil : Perfil.values()) {
				if(perfil.getCod() == cod) {
					return perfil;
				}
			}
		}
		throw new IllegalArgumentException("Código digitado é inválido");
	}
}
