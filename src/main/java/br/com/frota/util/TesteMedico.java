package br.com.frota.util;

import br.com.frota.DAO.MedicoDAO;
import br.com.frota.model.Medico;
import br.com.frota.servico.ServicoMedico;

import java.sql.SQLException;
import java.util.List;

public class TesteMedico {
    static final MedicoDAO medicoDAO = new MedicoDAO();

    static final ServicoMedico servicoMedico = new ServicoMedico();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(medicoDAO.count());

        //salvar
        Medico medico = new Medico("CRM", "John Doe");
        servicoMedico.salvar(medico);
        long id = medico.getId();

        //buscar por ID
        medico = medicoDAO.findById(id);
        System.out.println(medico);

        //Update
        medico.setCrm("CRM-2");
        medico.setNome("Jane Doe");
        medicoDAO.update(medico);
        medico = medicoDAO.findById(id);
        System.out.println(medico);

        //Select all
        List<Medico> medicos = medicoDAO.selectAll();
        medicos.forEach(System.out::println);

        //Delete
        medicoDAO.delete(id);
        medicoDAO.selectAll().forEach(System.out::println);
    }
}
