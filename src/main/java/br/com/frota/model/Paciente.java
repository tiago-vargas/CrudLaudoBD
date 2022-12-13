package br.com.frota.model;

import java.sql.Date;

public class Paciente extends GenericModel {

    private String nome;
    private Date dtNascimento;

    public Paciente(long id, String nome, Date dtNascimento) {
        this.setId(id);
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public Paciente(String nome, Date dtNascimento) {
        super();
        this.nome = nome;
        this.dtNascimento = dtNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dt_nascimento) {
        this.dtNascimento = dt_nascimento;
    }

    @Override
    public String toString() {
        return "Paciente [nome=" + nome + ", dt_nascimento=" + dtNascimento + "]";
    }
}
