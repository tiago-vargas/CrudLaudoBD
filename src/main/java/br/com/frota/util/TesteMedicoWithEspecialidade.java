package br.com.frota.util;

import br.com.frota.model.Especialidade;
import br.com.frota.model.Medico;
import br.com.frota.servico.ServicoEspecialidade;
import br.com.frota.servico.ServicoMedico;

import java.sql.SQLException;
import java.util.List;

public class TesteMedicoWithEspecialidade {
    static final ServicoMedico servicoMedico = new ServicoMedico();
    static final ServicoEspecialidade servicoEspecialidade = new ServicoEspecialidade();

    public static void main(String[] args) throws SQLException {

        // Adicionar especialidade
        var medico = new Medico("CRM-1", "Name-1");
        servicoMedico.salvar(medico);
        var especialidade1 = new Especialidade("Esp-1", "Obs-1");
        servicoEspecialidade.salvar(especialidade1);
        var especialidade2 = new Especialidade("Esp-2", "Obs-2");
        servicoMedico.adicionarEspecialidade(medico.getId(), especialidade1.getId());
        servicoMedico.adicionarEspecialidade(medico.getId(), especialidade2.getId());

        // Buscar especialidades
        List<Especialidade> especialidades = servicoMedico.buscarEspecialidade(medico.getId());
        especialidades.forEach(System.out::println);

        // Buscar medicos com especialidade
        List<Medico> medicos = servicoEspecialidade.buscarMedicosComEspecialidade(especialidade2.getId());
        medicos.forEach(System.out::println);

        // Remover especialidade
        servicoMedico.removerEspecialidade(medico.getId(), especialidade2.getId());
        especialidades = servicoMedico.buscarEspecialidade(medico.getId());
        especialidades.forEach(System.out::println);


        // Delete
        servicoMedico.remover(medico.getId());
        servicoMedico.buscarTodos().forEach(System.out::println);

        servicoEspecialidade.remover(especialidade1.getId());
        servicoEspecialidade.remover(especialidade2.getId());
    }
}
