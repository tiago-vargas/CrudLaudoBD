package br.com.frota.model;

import java.sql.Date;

public class ConsultaMedica extends GenericModel {

    private Date dtConsulta;
    private long medicoId;
    private long pacienteId;
    private String nmAtendimento;

    public ConsultaMedica(Date dtConsulta, long medicoId, long pacienteId, String nmAtendimento) {
        super();
        this.medicoId = medicoId;
        this.dtConsulta = dtConsulta;
        this.pacienteId = pacienteId;
        this.nmAtendimento = nmAtendimento;
    }

    public ConsultaMedica(long id) {
        this.setId(id);
    }

    public Date getDtConsulta() {
        return dtConsulta;
    }

    public void setDtConsulta(Date dtConsulta) {
        this.dtConsulta = dtConsulta;
    }

    public long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(long medicoId) {
        this.medicoId = medicoId;
    }

    public long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public String getNmAtendimento() {
        return nmAtendimento;
    }

    public void setNmAtendimento(String nmAtendimento) {
        this.nmAtendimento = nmAtendimento;
    }


    @Override
    public String toString() {
        return "ConsultaMedica [dt_consulta=" + dtConsulta
                + ", medico_id=" + medicoId
                + ", paciente_id=" + pacienteId
                + ", nm_atendimento=" + nmAtendimento
                + "]";
    }
}
