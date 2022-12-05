package br.com.frota.servico;

import br.com.frota.DAO.MaterialExameDAO;
import br.com.frota.model.MaterialExame;

import java.sql.SQLException;

public class ServicoMaterialExame {

    private MaterialExameDAO medicoDAO = new MaterialExameDAO();

    public MaterialExame salvar(MaterialExame entidade) {
        return medicoDAO.insert(entidade);
    }

    public MaterialExame buscarPorId(Integer id) {
        return medicoDAO.findById(id);
    }

    public void update(MaterialExame medico) throws SQLException {
        medicoDAO.updateMaterialExame(medico);
    }

    public void remover(Integer id) throws SQLException {
        medicoDAO.deleteMaterialExame(id);
    }
}
