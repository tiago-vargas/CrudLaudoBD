package br.com.frota.servico;

import br.com.frota.DAO.MaterialExameDAO;
import br.com.frota.model.MaterialExame;

import java.sql.SQLException;

public class ServicoMaterialExame {

    private final MaterialExameDAO materialExameDAO = new MaterialExameDAO();

    public MaterialExame salvar(MaterialExame entidade) {
        return materialExameDAO.insert(entidade);
    }

    public MaterialExame buscarPorId(long id) {
        return materialExameDAO.findById(id);
    }

    public void update(MaterialExame medico) throws SQLException {
        materialExameDAO.updateMaterialExame(medico);
    }

    public void remover(long id) throws SQLException {
        materialExameDAO.deleteMaterialExame(id);
    }
}
