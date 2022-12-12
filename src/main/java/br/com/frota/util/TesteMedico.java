package br.com.frota.util;

import br.com.frota.DAO.MedicoDAO;
import br.com.frota.model.Medico;
import br.com.frota.servico.ServicoMedico;

import java.sql.SQLException;
import java.util.List;

public class TesteMedico {
    static MedicoDAO medicoDAO = new MedicoDAO();

    static ServicoMedico servicoMedico = new ServicoMedico();

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
        medicoDAO.updateMedico(medico);
        medico = medicoDAO.findById(id);
        System.out.println(medico);

        //Select all
        List<Medico> medicos = medicoDAO.selectAllMedico();
        medicos.forEach(System.out::println);

        //Delete
        medicoDAO.deleteMedico(id);
        medicoDAO.selectAllMedico().forEach(System.out::println);
    }
}
