package br.com.frota.servico;

import br.com.frota.DAO.PacienteDAO;
import br.com.frota.model.Paciente;

import java.sql.SQLException;

public class ServicoPaciente {

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    public Paciente salvar(Paciente entidade) {
        return pacienteDAO.insert(entidade);
    }

    public Paciente buscarPorId(long id) {
        return pacienteDAO.findById(id);
    }

    public void update(Paciente medico) throws SQLException {
        pacienteDAO.update(medico);
    }

    public void remover(long id) throws SQLException {
        pacienteDAO.delete(id);
    }
}
