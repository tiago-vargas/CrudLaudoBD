package br.com.frota.util;

import br.com.frota.DAO.TipoExameDAO;
import br.com.frota.model.TipoExame;
import br.com.frota.servico.ServicoTipoExame;

import java.sql.SQLException;
import java.util.List;

public class TesteTipoExame {
    static final TipoExameDAO tipoExameDAO = new TipoExameDAO();

    static final ServicoTipoExame servicoTipoExame = new ServicoTipoExame();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(tipoExameDAO.count());

        //salvar
        TipoExame tipoExame = new TipoExame("Descricao", "Observacao");
        servicoTipoExame.salvar(tipoExame);
        long id = tipoExame.getId();

        //buscar por ID
        tipoExame = tipoExameDAO.findById(id);
        System.out.println(tipoExame);

        //Update
        tipoExame.setDescricao("Descricao-2");
        tipoExame.setObservacao("Observacao-2");
        tipoExameDAO.updateTipoExame(tipoExame);
        tipoExame = tipoExameDAO.findById(id);
        System.out.println(tipoExame);

        //Select all
        List<TipoExame> tipoExames = tipoExameDAO.selectAllTipoExame();
        tipoExames.forEach(System.out::println);

        //Delete
        tipoExameDAO.deleteTipoExame(id);
        tipoExameDAO.selectAllTipoExame().forEach(System.out::println);
    }
}
