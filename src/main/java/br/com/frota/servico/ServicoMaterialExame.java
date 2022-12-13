package br.com.frota.servico;

import br.com.frota.DAO.MaterialExameDAO;
import br.com.frota.model.MaterialExame;

import java.sql.SQLException;
import java.util.List;

public class ServicoMaterialExame {

    private final MaterialExameDAO materialExameDAO = new MaterialExameDAO();

    public MaterialExame salvar(MaterialExame entidade) {
        return materialExameDAO.insert(entidade);
    }

    public MaterialExame buscarPorId(long id) {
        return materialExameDAO.findById(id);
    }

    public void update(MaterialExame medico) throws SQLException {
        materialExameDAO.update(medico);
    }

    public void remover(long id) throws SQLException {
        materialExameDAO.delete(id);
    }

    public int contar() {
        return materialExameDAO.count();
    }

    public List<MaterialExame> buscarTodos() {
        return materialExameDAO.selectAll();
    }
}
