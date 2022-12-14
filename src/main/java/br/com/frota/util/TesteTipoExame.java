package br.com.frota.util;

import br.com.frota.model.TipoExame;
import br.com.frota.servico.ServicoTipoExame;

import java.sql.SQLException;
import java.util.List;

public class TesteTipoExame {
    static final ServicoTipoExame servicoTipoExame = new ServicoTipoExame();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(servicoTipoExame.contar());

        //salvar
        TipoExame tipoExame = new TipoExame("D-1", "O-1");
        servicoTipoExame.salvar(tipoExame);

        //buscar por ID
        long id = tipoExame.getId();
        tipoExame = servicoTipoExame.buscarPorId(id);
        System.out.println(tipoExame);

        //Update
        tipoExame.setDescricao("D-2");
        tipoExame.setObservacao("O-2");
        servicoTipoExame.update(tipoExame);
        tipoExame = servicoTipoExame.buscarPorId(id);
        System.out.println(tipoExame);

        //Select all
        List<TipoExame> tipoExames = servicoTipoExame.buscarTodos();
        tipoExames.forEach(System.out::println);

        //Delete
        servicoTipoExame.remover(id);
        servicoTipoExame.buscarTodos().forEach(System.out::println);
    }
}
