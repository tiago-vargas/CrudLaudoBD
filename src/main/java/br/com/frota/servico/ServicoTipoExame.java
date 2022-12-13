package br.com.frota.servico;

import br.com.frota.DAO.TipoExameDAO;
import br.com.frota.model.TipoExame;

import java.sql.SQLException;

public class ServicoTipoExame {

    private final TipoExameDAO tipoExameDAO = new TipoExameDAO();

    public TipoExame salvar(TipoExame entidade) {
        return tipoExameDAO.insert(entidade);
    }

    public TipoExame buscarPorId(long id) {
        return tipoExameDAO.findById(id);
    }

    public void update(TipoExame medico) throws SQLException {
        tipoExameDAO.update(medico);
    }

    public void remover(long id) throws SQLException {
        tipoExameDAO.delete(id);
    }
}
