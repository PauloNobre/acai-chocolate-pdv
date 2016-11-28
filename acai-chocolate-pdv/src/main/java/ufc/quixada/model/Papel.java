package ufc.quixada.model;

import org.springframework.security.core.GrantedAuthority;

public enum Papel implements GrantedAuthority{
	ATENDENTE("Atendente"), GERENTE("Gerente");

	private String descricao;

	private Papel(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String getAuthority() {
		return this.toString();
	}
}
