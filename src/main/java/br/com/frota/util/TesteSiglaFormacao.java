package br.com.frota.util;

import br.com.frota.DAO.SiglaFormacaoDAO;
import br.com.frota.model.SiglaFormacao;
import br.com.frota.servico.ServicoSiglaFormacao;

import java.sql.SQLException;
import java.util.List;

public class TesteSiglaFormacao {
    static final SiglaFormacaoDAO siglaFormacaoDAO = new SiglaFormacaoDAO();

    static final ServicoSiglaFormacao servicoSiglaFormacao = new ServicoSiglaFormacao();

    public static void main(String[] args) throws SQLException {
        //count
        System.out.println(siglaFormacaoDAO.count());

        //salvar
        SiglaFormacao siglaFormacao = new SiglaFormacao("SIGLA");
        servicoSiglaFormacao.salvar(siglaFormacao);

        //buscar por ID
        long id = siglaFormacao.getId();
        siglaFormacao = siglaFormacaoDAO.findById(id);
        System.out.println(siglaFormacao);

        //Update
        siglaFormacao.setSigla("NOVA.SIGLA");
        siglaFormacaoDAO.update(siglaFormacao);
        siglaFormacao = siglaFormacaoDAO.findById(id);
        System.out.println(siglaFormacao);

        //Select all
        List<SiglaFormacao> siglasFormacoes = siglaFormacaoDAO.selectAll();
        siglasFormacoes.forEach(System.out::println);

        //Delete
        siglaFormacaoDAO.delete(id);
        siglaFormacaoDAO.selectAll().forEach(System.out::println);
    }
}
