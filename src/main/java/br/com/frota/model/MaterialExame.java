package br.com.frota.model;

public class MaterialExame extends GenericModel {

    private String material;
    private String observacao;

    public MaterialExame(Long id, String material, String observacao) {
        this.setId(id);
        this.material = material;
        this.observacao = observacao;
    }

    public MaterialExame(Long id, String observacao) {
        this.setId(id);
        this.observacao = observacao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Marca [crm=" + material + ", nome=" + observacao + "]";
    }
}
