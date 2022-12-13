package br.com.frota.util;

import br.com.frota.model.Paciente;
import br.com.frota.servico.ServicoPaciente;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TestePaciente {
    static final ServicoPaciente servicoPaciente = new ServicoPaciente();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoPaciente.contar());

        //salvar
        Paciente paciente = new Paciente("John Doe", new Date(1000000000000L));
        servicoPaciente.salvar(paciente);

        //buscar por ID
        long id = paciente.getId();
        paciente = servicoPaciente.buscarPorId(id);
        System.out.println(paciente);

        //Update
        paciente.setNome("Jane Doe");
        paciente.setDtNascimento(new Date(2000000000000L));
        servicoPaciente.update(paciente);
        paciente = servicoPaciente.buscarPorId(id);
        System.out.println(paciente);

        //Select all
        List<Paciente> pacientes = servicoPaciente.buscarTodos();
        pacientes.forEach(System.out::println);

        //Delete
        servicoPaciente.remover(id);
        servicoPaciente.buscarTodos().forEach(System.out::println);
    }
}
