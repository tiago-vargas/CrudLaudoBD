package br.com.frota.model;

public class MaterialExame extends GenericModel {

    private String material;
    private String observacao;

    public MaterialExame(String material, String observacao) {
        super();
        this.material = material;
        this.observacao = observacao;
    }

    public MaterialExame(long id) {
        this.setId(id);
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
        return "MaterialExame [material=" + material + ", observacao=" + observacao + "]";
    }
}
