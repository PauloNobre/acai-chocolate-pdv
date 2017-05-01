package ufc.quixada.model;

public enum FormaPagamento {

	À_VISTA("À Vista"), CARTAO("Cartão");

	private String descricao;

	private FormaPagamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static FormaPagamento fromDescricao(String descricao){
		for(FormaPagamento e : FormaPagamento.values()){
			if(e.getDescricao().equals(descricao)) {
				return e;
			}
		}
		return null;
	}
}
