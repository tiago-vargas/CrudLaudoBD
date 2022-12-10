package br.com.frota.model;

public class SiglaFormacao extends GenericModel {

    private String sigla;

    public SiglaFormacao(Long id, String sigla) {
        this.setId(id);
        this.sigla = sigla;
    }

    public SiglaFormacao(Long id) {
        this.setId(id);
    }

    public SiglaFormacao(String sigla) {
        super();
        this.sigla = sigla;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "SiglaFormacao [sigla=" + sigla + "]";
    }
}
