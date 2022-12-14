package br.com.frota.util;

import br.com.frota.model.UnidadeMedida;
import br.com.frota.servico.ServicoUnidadeMedida;

import java.sql.SQLException;
import java.util.List;

public class TesteUnidadeMedida {

    static final ServicoUnidadeMedida servicoUnidadeMedida = new ServicoUnidadeMedida();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoUnidadeMedida.contar());

        //salvar
        UnidadeMedida unidadeMedida = new UnidadeMedida("J/s");
        servicoUnidadeMedida.salvar(unidadeMedida);
        long id = unidadeMedida.getId();

        //buscar por ID
        unidadeMedida = servicoUnidadeMedida.buscarPorId(id);
        System.out.println(unidadeMedida);

        //Update
        unidadeMedida.setDescricao("W");
        servicoUnidadeMedida.update(unidadeMedida);
        unidadeMedida = servicoUnidadeMedida.buscarPorId(id);
        System.out.println(unidadeMedida);

        //Select all
        List<UnidadeMedida> unidadeMedidas = servicoUnidadeMedida.buscarTodos();
        unidadeMedidas.forEach(System.out::println);

        //Delete
        servicoUnidadeMedida.remover(id);
        servicoUnidadeMedida.buscarTodos().forEach(System.out::println);
    }
}
