package br.com.frota.servico;

import br.com.frota.DAO.UnidadeMedidaDAO;
import br.com.frota.model.UnidadeMedida;

import java.sql.SQLException;

public class ServicoUnidadeMedida {

    private final UnidadeMedidaDAO unidadeMedidaDAO = new UnidadeMedidaDAO();

    public UnidadeMedida salvar(UnidadeMedida entidade) {
        return unidadeMedidaDAO.insert(entidade);
    }

    public UnidadeMedida buscarPorId(long id) {
        return unidadeMedidaDAO.findById(id);
    }

    public void update(UnidadeMedida unidadeMedida) throws SQLException {
        unidadeMedidaDAO.update(unidadeMedida);
    }

    public void remover(long id) throws SQLException {
        unidadeMedidaDAO.delete(id);
    }
}
