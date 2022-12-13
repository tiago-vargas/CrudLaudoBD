package br.com.frota.util;

import br.com.frota.DAO.PacienteDAO;
import br.com.frota.model.Paciente;
import br.com.frota.servico.ServicoPaciente;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class TestePaciente {
    static final PacienteDAO pacienteDAO = new PacienteDAO();

    static final ServicoPaciente servicoPaciente = new ServicoPaciente();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(pacienteDAO.count());

        //salvar
        Paciente paciente = new Paciente("John Doe", new Date(10000));
        servicoPaciente.salvar(paciente);
        long id = paciente.getId();

        //buscar por ID
        paciente = pacienteDAO.findById(id);
        System.out.println(paciente);

        //Update
        paciente.setNome("Jane Doe");
        paciente.setDtNascimento(new Date(20000));
        pacienteDAO.updatePaciente(paciente);
        paciente = pacienteDAO.findById(id);
        System.out.println(paciente);

        //Select all
        List<Paciente> pacientes = pacienteDAO.selectAllPaciente();
        pacientes.forEach(System.out::println);

        //Delete
        pacienteDAO.deletePaciente(id);
        pacienteDAO.selectAllPaciente().forEach(System.out::println);
    }
}
