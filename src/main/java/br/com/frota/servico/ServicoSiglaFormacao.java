package br.com.frota.servico;

import br.com.frota.DAO.SiglaFormacaoDAO;
import br.com.frota.model.SiglaFormacao;

import java.sql.SQLException;
import java.util.List;

public class ServicoSiglaFormacao {

    private final SiglaFormacaoDAO siglaFormacaoDAO = new SiglaFormacaoDAO();

    public SiglaFormacao salvar(SiglaFormacao entidade) {
        return siglaFormacaoDAO.insert(entidade);
    }

    public SiglaFormacao buscarPorId(Long id) {
        return siglaFormacaoDAO.findById(id);
    }

    public List<SiglaFormacao> buscarTodos() {
        return siglaFormacaoDAO.selectAll();
    }

    public void update(SiglaFormacao siglaFormacao) throws SQLException {
        siglaFormacaoDAO.update(siglaFormacao);
    }

    public void remover(Long id) throws SQLException {
        siglaFormacaoDAO.delete(id);
    }

    public int contar() {
        return siglaFormacaoDAO.count();
    }
}
