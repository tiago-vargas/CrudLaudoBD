package br.com.frota.model;

public class Medico extends GenericModel {

    private String crm;
    private String nome;

    public Medico(Long id, String crm, String nome) {
        this.setId(id);
        this.crm = crm;
        this.nome = nome;
    }

    public Medico(String crm, String nome) {
        super();
        this.crm = crm;
        this.nome = nome;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Marca [crm=" + crm + ", nome=" + nome + "]";
    }
}
