package br.com.frota.model;

public class Marca extends GenericModel {

	private String descricao;

	private String observacao;

	public Marca(Long id, String descricao, String observacao) {
		this.setId(id);
		this.descricao = descricao;
		this.observacao = observacao;
	}

	public Marca(String descricao, String observacao) {
		super();
		this.descricao = descricao;
		this.observacao = observacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public String toString() {
		return "Marca [descricao=" + descricao + ", observacao=" + observacao + "]";
	}
}
