package br.com.frota.model;

import br.com.frota.servico.ServicoSiglaFormacao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class TestSiglaFormacao {
    @Test
    void salvar() throws SQLException {
        var siglaFormacao = new SiglaFormacao("Sigla_salvar");

        var servico = new ServicoSiglaFormacao();
        servico.salvar(siglaFormacao);

        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://200.17.32.221:5432/tiagovargas20211045050365",
                "postgres",
                "postgres");
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM sigla_formacao");

        String sigla = null;
        while (rs.next()) {
            sigla = rs.getString("sigla");
            Assertions.assertEquals("Sigla_salvar", sigla);
        }
        Assertions.assertNotNull(sigla);
    }
}
