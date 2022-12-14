package br.com.frota.util;

import br.com.frota.model.SiglaFormacao;
import br.com.frota.servico.ServicoSiglaFormacao;

import java.sql.SQLException;
import java.util.List;

public class TesteSiglaFormacao {
    static final ServicoSiglaFormacao servicoSiglaFormacao = new ServicoSiglaFormacao();

    public static void main(String[] args) throws SQLException {
        //count
        System.out.println(servicoSiglaFormacao.contar());

        //salvar
        SiglaFormacao siglaFormacao = new SiglaFormacao("SIGLA");
        servicoSiglaFormacao.salvar(siglaFormacao);

        //buscar por ID
        long id = siglaFormacao.getId();
        siglaFormacao = servicoSiglaFormacao.buscarPorId(id);
        System.out.println(siglaFormacao);

        //Update
        siglaFormacao.setSigla("NOVA.SIGLA");
        servicoSiglaFormacao.update(siglaFormacao);
        siglaFormacao = servicoSiglaFormacao.buscarPorId(id);
        System.out.println(siglaFormacao);

        //Select all
        List<SiglaFormacao> siglasFormacoes = servicoSiglaFormacao.buscarTodos();
        siglasFormacoes.forEach(System.out::println);

        //Delete
        servicoSiglaFormacao.remover(id);
        servicoSiglaFormacao.buscarTodos().forEach(System.out::println);
    }
}
