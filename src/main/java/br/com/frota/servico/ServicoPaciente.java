package br.com.frota.servico;

import br.com.frota.DAO.PacienteDAO;
import br.com.frota.model.Paciente;

import java.sql.SQLException;

public class ServicoPaciente {

    private PacienteDAO pacienteDAO = new PacienteDAO();

    public Paciente salvar(Paciente entidade) {
        return pacienteDAO.insert(entidade);
    }

    public Paciente buscarPorId(Integer id) {
        return pacienteDAO.findById(id);
    }

    public void update(Paciente medico) throws SQLException {
        pacienteDAO.updatePaciente(medico);
    }

    public void remover(Integer id) throws SQLException {
        pacienteDAO.deletePaciente(id);
    }
}
