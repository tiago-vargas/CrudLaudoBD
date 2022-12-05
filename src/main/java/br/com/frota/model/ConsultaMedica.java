package br.com.frota.model;

import java.sql.Date;

public class ConsultaMedica extends GenericModel {

    private Date dtConsulta;
    private int medicoId;
    private int pacienteId;
    private String nmAtendimento;

    public ConsultaMedica(Long id, Date dtConsulta, int medicoId, int pacienteId, String nmAtendimento) {
        this.setId(id);
        this.medicoId = medicoId;
        this.dtConsulta = dtConsulta;
        this.pacienteId = pacienteId;
        this.nmAtendimento = nmAtendimento;
    }

    public int getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(int medicoId) {
        this.medicoId = medicoId;
    }

    public Date getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(Date dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    @Override
    public String toString() {
        return "Marca [dt_consulta=" + dtConsulta + ", medico_id=" + medicoId + ", paciente_id" + pacienteId + ", nm_atendimento=" + nmAtendimento + "]";
    }
}
