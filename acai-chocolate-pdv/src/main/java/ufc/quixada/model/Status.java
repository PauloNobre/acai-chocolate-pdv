package ufc.quixada.model;

public enum Status {
	NOVA("Nova"), ANDAMENTO("Andamento"), FINALIZADA("Finalizada");
	
	private String descricao;

	private Status(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
