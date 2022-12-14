package br.com.frota.servico;

import br.com.frota.DAO.MedicoDAO;
import br.com.frota.model.Especialidade;
import br.com.frota.model.Medico;

import java.sql.SQLException;
import java.util.List;

public class ServicoMedico {

    private final MedicoDAO medicoDAO = new MedicoDAO();

    public Medico salvar(Medico entidade) {
        return medicoDAO.insert(entidade);
    }

    public Medico buscarPorId(long id) {
        return medicoDAO.findById(id);
    }

    public List<Especialidade> buscarEspecialidade(long medicoId) {
        return  medicoDAO.selectEspecialidadeOfMedico(medicoId);
    }

    public void update(Medico medico) throws SQLException {
        medicoDAO.update(medico);
    }

    public void remover(long id) throws SQLException {
        medicoDAO.delete(id);
    }

    public int contar() {
        return medicoDAO.count();
    }

    public List<Medico> buscarTodos() {
        return medicoDAO.selectAll();
    }

    public void adicionarEspecialidade(long medicoId, long especialidadeId) {
        medicoDAO.insertEspecialidade(medicoId, especialidadeId);
    }

    public void removerEspecialidade(long medicoId, long especialidadeId) throws SQLException {
        medicoDAO.removeEspecialidade(medicoId, especialidadeId);
    }
}
