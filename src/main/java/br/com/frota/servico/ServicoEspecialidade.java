package br.com.frota.servico;

import br.com.frota.DAO.EspecialidadeDAO;
import br.com.frota.model.Especialidade;
import br.com.frota.model.Medico;

import java.sql.SQLException;
import java.util.List;

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

    public int contar() {
        return especialidadeDAO.count();
    }

    public List<Especialidade> buscarTodos() {
        return especialidadeDAO.selectAll();
    }

    public List<Medico> buscarMedicosComEspecialidade(long medicoId) {
        return  especialidadeDAO.selectMedicosWithEspecialidade(medicoId);
    }
}
