package br.com.frota.util;

import br.com.frota.model.ConsultaMedica;
import br.com.frota.model.Medico;
import br.com.frota.model.Paciente;
import br.com.frota.servico.ServicoConsultaMedica;
import br.com.frota.servico.ServicoMedico;
import br.com.frota.servico.ServicoPaciente;

import java.sql.Date;
import java.sql.SQLException;

public class TesteConsultaMedica {
    static final ServicoConsultaMedica servicoConsultaMedica = new ServicoConsultaMedica();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoConsultaMedica.contar());

        var medico1 = new Medico("CRM-1", "Nome do medico 1");
        var medico2 = new Medico("CRM-2", "Nome do medico 2");
        var paciente1 = new Paciente("Nome do paciente 1", new Date(10000));
        var paciente2 = new Paciente("Nome do paciente 2", new Date(10000));

        var servicoMedico = new ServicoMedico();
        var servicoPaciente = new ServicoPaciente();

        servicoMedico.salvar(medico1);
        servicoMedico.salvar(medico2);
        servicoPaciente.salvar(paciente1);
        servicoPaciente.salvar(paciente2);

        //salvar
        var consultaMedica =
                new ConsultaMedica(new Date(1222111000), medico1.getId(), paciente1.getId(), "NA-1");
        servicoConsultaMedica.salvar(consultaMedica);

        //buscar por ID
        long id = consultaMedica.getId();
        consultaMedica = servicoConsultaMedica.buscarPorId(id);
        System.out.println(consultaMedica);

        //Update
        consultaMedica.setDtConsulta(new Date(1400222111000L));
        consultaMedica.setMedicoId(medico2.getId());
        consultaMedica.setPacienteId(paciente2.getId());
        consultaMedica.setNmAtendimento("NA-2");
        servicoConsultaMedica.update(consultaMedica);
        consultaMedica = servicoConsultaMedica.buscarPorId(id);
        System.out.println(consultaMedica);

        //Select all
        servicoConsultaMedica.buscarTodos().forEach(System.out::println);

        //Delete
        servicoConsultaMedica.remover(id);
        servicoConsultaMedica.buscarTodos().forEach(System.out::println);

        servicoMedico.remover(medico1.getId());
        servicoMedico.remover(medico2.getId());
        servicoPaciente.remover(paciente1.getId());
        servicoPaciente.remover(paciente2.getId());
    }
}
