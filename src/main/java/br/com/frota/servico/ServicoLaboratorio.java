package br.com.frota.servico;

import br.com.frota.DAO.LaboratorioDAO;
import br.com.frota.model.Laboratorio;

import java.sql.SQLException;

public class ServicoLaboratorio {

    private LaboratorioDAO laboratorioDAO = new LaboratorioDAO();

    public Laboratorio salvar(Laboratorio entidade) {
        return laboratorioDAO.insert(entidade);
    }

    public Laboratorio buscarPorId(Integer id) {
        return laboratorioDAO.findById(id);
    }

    public void update(Laboratorio laboratorio) throws SQLException {
        laboratorioDAO.updateLaboratorio(laboratorio);
    }

    public void remover(Integer id) throws SQLException {
        laboratorioDAO.deleteLaboratorio(id);
    }
}
