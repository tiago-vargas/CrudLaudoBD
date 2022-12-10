package br.com.frota.servico;

import br.com.frota.DAO.SiglaFormacaoDAO;
import br.com.frota.model.SiglaFormacao;

import java.sql.SQLException;

public class ServicoSiglaFormacao {

    private SiglaFormacaoDAO siglaFormacaoDAO = new SiglaFormacaoDAO();

    public SiglaFormacao salvar(SiglaFormacao entidade) {
        return siglaFormacaoDAO.insert(entidade);
    }

    public SiglaFormacao buscarPorId(Long id) {
        return siglaFormacaoDAO.findById(id);
    }

    public void update(SiglaFormacao medico) throws SQLException {
        siglaFormacaoDAO.updateSiglaFormacao(medico);
    }

    public void remover(Long id) throws SQLException {
        siglaFormacaoDAO.deleteSiglaFormacao(id);
    }
}
