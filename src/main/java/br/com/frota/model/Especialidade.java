package br.com.frota.model;

public class Especialidade extends GenericModel {

    private String descricao;
    private String observacao;

    public Especialidade(long id, String descricao, String observacao) {
        this.setId(id);
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public Especialidade(String descricao, String observacao) {
        super();
        this.descricao = descricao;
        this.observacao = observacao;
    }

    public Especialidade(long id) {
        this.setId(id);
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
        return "Especialidade [descricao=" + descricao + ", observacao=" + observacao + "]";
    }
}
