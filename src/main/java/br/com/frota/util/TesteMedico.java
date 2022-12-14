package br.com.frota.util;

import br.com.frota.model.Medico;
import br.com.frota.servico.ServicoMedico;

import java.sql.SQLException;
import java.util.List;

public class TesteMedico {
    static final ServicoMedico servicoMedico = new ServicoMedico();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoMedico.contar());

        //salvar
        Medico medico = new Medico("CRM-1", "Name-1");
        servicoMedico.salvar(medico);

        //buscar por ID
        long id = medico.getId();
        medico = servicoMedico.buscarPorId(id);
        System.out.println(medico);

        //Update
        medico.setCrm("CRM-2");
        medico.setNome("Name-2");
        servicoMedico.update(medico);
        medico = servicoMedico.buscarPorId(id);
        System.out.println(medico);

        //Select all
        List<Medico> medicos = servicoMedico.buscarTodos();
        medicos.forEach(System.out::println);

        //Delete
        servicoMedico.remover(id);
        servicoMedico.buscarTodos().forEach(System.out::println);
    }
}
