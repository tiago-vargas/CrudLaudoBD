package br.com.frota.model;

import br.com.frota.DAO.SiglaFormacaoDAO;
import br.com.frota.servico.ServicoSiglaFormacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestSiglaFormacao {
//    @Test
//    void count() throws SQLException {
//
//    }
//
//    @Test
//    void salvar() {
//        var siglaFormacaoDAO = new SiglaFormacaoDAO();
//    }
//
//    @Test
//    void buscarPorId() {
//        var siglaFormacaoDAO = new SiglaFormacaoDAO();
//    }
//
//    @Test
//    void update() {
//        var siglaFormacaoDAO = new SiglaFormacaoDAO();
//    }
//
//    @Test
//    void selectAll() {
//        var siglaFormacaoDAO = new SiglaFormacaoDAO();
//    }
//
//    @Test
//    void delete() {
//        var siglaFormacaoDAO = new SiglaFormacaoDAO();
//    }

    @Test
    void allMethods() {
        var servico = new ServicoSiglaFormacao();
        var dao = new SiglaFormacaoDAO();

        int currentCount = dao.count();

        var siglaFormacao1 = new SiglaFormacao("Sigla1");

        servico.salvar(siglaFormacao1);

        Connection con = null;
        Statement stmt = null;
        try {
            con = DriverManager.getConnection(
                    "jdbc:postgresql://200.17.32.221:5432/tiagovargas20211045050365",
                    "postgres",
                    "postgres");
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sigla_formacao");

            while (rs.next()) {
                String sigla = rs.getString("sigla");

                Assertions.assertEquals("Sigla1", sigla);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        int initialCount = servico.count();
        Assertions.assertEquals(currentCount + 1, dao.count());

        SiglaFormacao s1 = servico.buscarPorId(siglaFormacao1.getId());
        Assertions.assertEquals(siglaFormacao1.getId(), s1.getId());
        Assertions.assertEquals(siglaFormacao1.getSigla(), s1.getSigla());

//        var siglaFormacao2Atualizado = new SiglaFormacao(siglaFormacao1.getId(), "NewSigla2");
//        try {
//            servico.update(siglaFormacao2Atualizado);
//            Assertions.assertEquals();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

//        servico.selectAll()

        try {
            servico.remover(siglaFormacao1.getId());

            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sigla_formacao");

            Assertions.assertFalse(rs.next());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        salvar 1
//        salvar 2
//        count
//        buscarPorId
//        update
//        selectAll
//        delete 2
//        delete 1
    }
}
