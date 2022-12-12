package br.com.frota.servico;

import br.com.frota.DAO.MedicoDAO;
import br.com.frota.model.Medico;

import java.sql.SQLException;

public class ServicoMedico {

    private final MedicoDAO medicoDAO = new MedicoDAO();

    public Medico salvar(Medico entidade) {
        return medicoDAO.insert(entidade);
    }

    public Medico buscarPorId(long id) {
        return medicoDAO.findById(id);
    }

    public void update(Medico medico) throws SQLException {
        medicoDAO.updateMedico(medico);
    }

    public void remover(long id) throws SQLException {
        medicoDAO.deleteMedico(id);
    }
}
