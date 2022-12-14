package br.com.frota.model;

public class MedicoHasEspecialidade extends GenericModel {

    private long medicoId;
    private long especialidadeId;

    public MedicoHasEspecialidade(long medicoId, long especialidadeId) {
        super();
        this.medicoId = medicoId;
        this.especialidadeId = especialidadeId;
    }

    public long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(long medicoId) {
        this.medicoId = medicoId;
    }

    public long getEspecialidadeId() {
        return especialidadeId;
    }

    public void setEspecialidadeId(long especialidadeId) {
        this.especialidadeId = especialidadeId;
    }

    @Override
    public String toString() {
        return "MedicoHasEspecialidade [medico_id=" + medicoId
                + ", especialidade_id=" + especialidadeId
                + "]";
    }
}
