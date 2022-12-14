package br.com.frota.model;

public class Laboratorio extends GenericModel {

    private String descricao;
    private String cnes;
    private String cnpj;
    private String crbm;
    private String nomeFantasia;

    public Laboratorio(String descricao, String cnes, String cnpj, String crbm, String nomeFantasia) {
        super();
        this.descricao = descricao;
        this.cnes = cnes;
        this.cnpj = cnpj;
        this.crbm = crbm;
        this.nomeFantasia = nomeFantasia;
    }

    public Laboratorio(long id) {
        this.setId(id);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCnes() {
        return cnes;
    }

    public void setCnes(String cnes) {
        this.cnes = cnes;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCrbm() {
        return crbm;
    }

    public void setCrbm(String crbm) {
        this.crbm = crbm;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }
    @Override
    public String toString() {
        return "Laboratorio [descricao=" + descricao
                + ", cnes=" + cnes
                + ", cnpj=" + cnpj
                + ", crbm=" + crbm
                + ", nome_fantasia=" + nomeFantasia
                + "]";
    }

}
