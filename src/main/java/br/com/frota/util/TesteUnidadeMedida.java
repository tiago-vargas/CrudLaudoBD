package br.com.frota.util;

import br.com.frota.DAO.UnidadeMedidaDAO;
import br.com.frota.model.UnidadeMedida;
import br.com.frota.servico.ServicoUnidadeMedida;

import java.sql.SQLException;
import java.util.List;

public class TesteUnidadeMedida {
    static final UnidadeMedidaDAO unidadeMedidaDAO = new UnidadeMedidaDAO();

    static final ServicoUnidadeMedida servicoUnidadeMedida = new ServicoUnidadeMedida();

    public static void main(String[] args) throws SQLException {

        //count
        System.out.println(unidadeMedidaDAO.count());

        //salvar
        UnidadeMedida unidadeMedida = new UnidadeMedida("J/s");
        servicoUnidadeMedida.salvar(unidadeMedida);
        long id = unidadeMedida.getId();

        //buscar por ID
        unidadeMedida = unidadeMedidaDAO.findById(id);
        System.out.println(unidadeMedida);

        //Update
        unidadeMedida.setDescricao("W");
        unidadeMedidaDAO.update(unidadeMedida);
        unidadeMedida = unidadeMedidaDAO.findById(id);
        System.out.println(unidadeMedida);

        //Select all
        List<UnidadeMedida> unidadeMedidas = unidadeMedidaDAO.selectAll();
        unidadeMedidas.forEach(System.out::println);

        //Delete
        unidadeMedidaDAO.delete(id);
        unidadeMedidaDAO.selectAll().forEach(System.out::println);
    }
}
