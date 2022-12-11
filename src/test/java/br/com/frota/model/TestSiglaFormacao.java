package br.com.frota.model;

import br.com.frota.servico.ServicoSiglaFormacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

public class TestSiglaFormacao {
    private static final String MY_DATABASE_URL = "jdbc:postgresql://200.17.32.221:5432/tiagovargas20211045050365";

    @Test
    void salvar() throws SQLException {
        var siglaFormacao = new SiglaFormacao("Sigla_salvar");

        var servico = new ServicoSiglaFormacao();
        servico.salvar(siglaFormacao);

        Connection con = DriverManager.getConnection(MY_DATABASE_URL, "postgres", "postgres");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM sigla_formacao WHERE id = " + siglaFormacao.getId() + ";");

        Assertions.assertTrue(rs.next());
        String sigla = rs.getString("sigla");
        Assertions.assertEquals("Sigla_salvar", sigla);

        // TearDown
        stmt.execute("DELETE FROM sigla_formacao WHERE id = " + siglaFormacao.getId() + ";");
    }

    @Test
    void buscarPorId() throws SQLException {
        var servico = new ServicoSiglaFormacao();

        Connection con = DriverManager.getConnection(MY_DATABASE_URL, "postgres", "postgres");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("INSERT INTO sigla_formacao (sigla) VALUES ('Sigla_buscar') RETURNING id;");

        Assertions.assertTrue(rs.next());
        long inserted_id = rs.getLong("id");


        SiglaFormacao result = servico.buscarPorId(inserted_id);

        Assertions.assertEquals("Sigla_buscar", result.getSigla());

        // TearDown
        stmt.execute("DELETE FROM sigla_formacao WHERE id = " + inserted_id + ";");
    }

    @Test
    void selectAll() throws SQLException {
        // SetUp
        Connection con = DriverManager.getConnection(MY_DATABASE_URL, "postgres", "postgres");
        Statement stmt = con.createStatement();
        stmt.execute("INSERT INTO sigla_formacao (sigla) VALUES ('sigla1');");
        stmt.execute("INSERT INTO sigla_formacao (sigla) VALUES ('sigla2');");

        // Act
        var servico = new ServicoSiglaFormacao();
        List<SiglaFormacao> list = servico.buscarTodos();

        Assertions.assertEquals("sigla1", list.get(0).getSigla());
        Assertions.assertEquals("sigla2", list.get(1).getSigla());

        // TearDown
        stmt.execute("DELETE FROM sigla_formacao WHERE id = " + list.get(0).getId() + ";");
        stmt.execute("DELETE FROM sigla_formacao WHERE id = " + list.get(1).getId() + ";");
    }

    @Test
    void count() throws SQLException {
        // SetUp
        var servico = new ServicoSiglaFormacao();
        int initialCount = servico.contar();
        Connection con = DriverManager.getConnection(MY_DATABASE_URL, "postgres", "postgres");
        Statement stmt = con.createStatement();

        // Act
        ResultSet rs = stmt.executeQuery("INSERT INTO sigla_formacao (sigla) VALUES ('Sigla_count') RETURNING id;");
        rs.next();
        long id = rs.getLong("id");
        int newCount = servico.contar();

        // Assert
        Assertions.assertEquals(initialCount + 1, newCount);

        // TearDown
        stmt.execute("DELETE FROM sigla_formacao WHERE id = " + id + ";");
    }
}
