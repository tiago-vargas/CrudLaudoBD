package br.com.frota.servico;

import br.com.frota.DAO.LaboratorioDAO;
import br.com.frota.model.Laboratorio;

import java.sql.SQLException;

public class ServicoLaboratorio {

    private final LaboratorioDAO laboratorioDAO = new LaboratorioDAO();

    public Laboratorio salvar(Laboratorio entidade) {
        return laboratorioDAO.insert(entidade);
    }

    public Laboratorio buscarPorId(long id) {
        return laboratorioDAO.findById(id);
    }

    public void update(Laboratorio laboratorio) throws SQLException {
        laboratorioDAO.update(laboratorio);
    }

    public void remover(long id) throws SQLException {
        laboratorioDAO.delete(id);
    }
}
