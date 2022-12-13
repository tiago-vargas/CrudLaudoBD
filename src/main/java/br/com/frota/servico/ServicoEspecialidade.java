package br.com.frota.servico;

import br.com.frota.DAO.EspecialidadeDAO;
import br.com.frota.model.Especialidade;

import java.sql.SQLException;

public class ServicoEspecialidade {

    private final EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();

    public Especialidade salvar(Especialidade entidade) {
        return especialidadeDAO.insert(entidade);
    }

    public Especialidade buscarPorId(long id) {
        return especialidadeDAO.findById(id);
    }

    public void update(Especialidade medico) throws SQLException {
        especialidadeDAO.update(medico);
    }

    public void remover(long id) throws SQLException {
        especialidadeDAO.delete(id);
    }
}
