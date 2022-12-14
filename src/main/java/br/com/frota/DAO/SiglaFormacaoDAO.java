package br.com.frota.DAO;

import br.com.frota.model.SiglaFormacao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SiglaFormacaoDAO extends GenericDAO {

    private static final String INSERT_SIGLA_FORMACAO_SQL = "INSERT INTO sigla_formacao (sigla) VALUES (?);";
    private static final String SELECT_SIGLA_FORMACAO_BY_ID_SQL = "SELECT * FROM sigla_formacao WHERE id = ?";
    private static final String SELECT_ALL_SIGLA_FORMACAO_SQL = "SELECT * FROM sigla_formacao;";
    private static final String DELETE_SIGLA_FORMACAO_SQL = "DELETE FROM sigla_formacao WHERE id = ?;";
    private static final String UPDATE_SIGLA_FORMACAO_SQL = "UPDATE sigla_formacao SET sigla = ? WHERE id = ?;";
    private static final String COUNT_SQL = "SELECT count(1) FROM sigla_formacao;";

    public int count() {
        return super.count(COUNT_SQL);
    }

    public SiglaFormacao insert(SiglaFormacao entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_SIGLA_FORMACAO_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            injectAllValues(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                entidade.setId(rs.getLong("id"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public SiglaFormacao findById(long id) {
        SiglaFormacao entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_SIGLA_FORMACAO_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                entidade = new SiglaFormacao(id);
                populateWithValues(entidade, rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public List<SiglaFormacao> selectAll() {
        List<SiglaFormacao> entidades = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_SIGLA_FORMACAO_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long id = rs.getLong("id");
                var entidade = new SiglaFormacao(id);
                populateWithValues(entidade, rs);

                entidades.add(entidade);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidades;
    }

    public boolean delete(Long id) throws SQLException {
        return super.delete(DELETE_SIGLA_FORMACAO_SQL, id);
    }

    public void update(SiglaFormacao entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_SIGLA_FORMACAO_SQL)) {
            injectAllValuesWithId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesWithId(SiglaFormacao entidade, PreparedStatement statement) throws SQLException {
        injectAllValues(entidade, statement);
        statement.setLong(2, entidade.getId());
    }

    private static void injectAllValues(SiglaFormacao entidade, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entidade.getSigla());
    }

    private static void populateWithValues(SiglaFormacao entidade, ResultSet rs) throws SQLException {
        String sigla = rs.getString("sigla");
        entidade.setSigla(sigla);
    }
}

