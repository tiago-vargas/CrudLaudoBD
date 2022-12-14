package br.com.frota.DAO;

import br.com.frota.model.TipoExame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipoExameDAO extends GenericDAO {

    private static final String INSERT_TIPO_EXAME_SQL =
            "INSERT INTO tipo_exame (descricao, observacao) VALUES (?, ?);";
    private static final String SELECT_TIPO_EXAME_BY_ID_SQL = "SELECT * FROM tipo_exame WHERE id = ?";
    private static final String SELECT_ALL_TIPO_EXAME_SQL = "SELECT * FROM tipo_exame;";
    private static final String DELETE_TIPO_EXAME_SQL = "DELETE FROM tipo_exame WHERE id = ?;";
    private static final String UPDATE_TIPO_EXAME_SQL =
            "UPDATE tipo_exame SET descricao = ?, observacao = ? WHERE id = ?;";
    private static final String COUNT_SQL = "SELECT count(1) FROM tipo_exame;";

    public int count() {
        return super.count(COUNT_SQL);
    }

    public TipoExame insert(TipoExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_TIPO_EXAME_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            injectAllValues(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                entidade.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public TipoExame findById(long id) {
        TipoExame entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_TIPO_EXAME_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                entidade = new TipoExame(id);
                populateWithValues(entidade, rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public List<TipoExame> selectAll() {
        List<TipoExame> entidades = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_TIPO_EXAME_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long id = rs.getLong("id");
                var entidade = new TipoExame(id);
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

    public boolean delete(long id) throws SQLException {
        return super.delete(DELETE_TIPO_EXAME_SQL, id);
    }

    public void update(TipoExame entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_TIPO_EXAME_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(TipoExame entidade, PreparedStatement statement) throws SQLException {
        injectAllValues(entidade, statement);
        statement.setLong(3, entidade.getId());
    }

    private static void injectAllValues(TipoExame entidade, PreparedStatement statement) throws SQLException {
        statement.setString(1, entidade.getDescricao());
        statement.setString(2, entidade.getObservacao());
    }

    private static void populateWithValues(TipoExame entidade, ResultSet rs) throws SQLException {
        String descricao = rs.getString("descricao");
        String observacao = rs.getString("observacao");
        entidade.setDescricao(descricao);
        entidade.setObservacao(observacao);
    }
}
