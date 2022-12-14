package br.com.frota.util;

import br.com.frota.model.Especialidade;
import br.com.frota.servico.ServicoEspecialidade;

import java.sql.SQLException;

public class TesteEspecialidade {
    static final ServicoEspecialidade servicoEspecialidade = new ServicoEspecialidade();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoEspecialidade.contar());

        //salvar
        Especialidade especialidade = new Especialidade("D-1", "O-1");
        servicoEspecialidade.salvar(especialidade);

        //buscar por ID
        long id = especialidade.getId();
        especialidade = servicoEspecialidade.buscarPorId(id);
        System.out.println(especialidade);

        //Update
        especialidade.setDescricao("D-2");
        especialidade.setObservacao("O-2");
        servicoEspecialidade.update(especialidade);
        especialidade = servicoEspecialidade.buscarPorId(id);
        System.out.println(especialidade);

        //Select all
        servicoEspecialidade.buscarTodos().forEach(System.out::println);

        //Delete
        servicoEspecialidade.remover(id);
        servicoEspecialidade.buscarTodos().forEach(System.out::println);
    }
}
