package br.com.frota.servico;

import br.com.frota.DAO.ConsultaMedicaDAO;
import br.com.frota.model.ConsultaMedica;

import java.sql.SQLException;
import java.util.List;

public class ServicoConsultaMedica {

    private final ConsultaMedicaDAO consultaMedicaDAO = new ConsultaMedicaDAO();

    public ConsultaMedica salvar(ConsultaMedica entidade) {
        return consultaMedicaDAO.insert(entidade);
    }

    public ConsultaMedica buscarPorId(long id) {
        return consultaMedicaDAO.findById(id);
    }

    public void update(ConsultaMedica medico) throws SQLException {
        consultaMedicaDAO.update(medico);
    }

    public void remover(long id) throws SQLException {
        consultaMedicaDAO.delete(id);
    }

    public int contar() {
        return consultaMedicaDAO.count();
    }

    public List<ConsultaMedica> buscarTodos() {
        return consultaMedicaDAO.selectAll();
    }
}
