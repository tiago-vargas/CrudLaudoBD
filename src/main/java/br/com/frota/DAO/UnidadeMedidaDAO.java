package br.com.frota.DAO;

import br.com.frota.model.UnidadeMedida;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnidadeMedidaDAO extends GenericDAO {

    private static final String INSERT_UNIDADE_MEDIDA_SQL = "INSERT INTO unidade_medida (descricao) VALUES (?);";
    private static final String SELECT_UNIDADE_MEDIDA_BY_ID_SQL = "SELECT * FROM unidade_medida WHERE id = ?";
    private static final String SELECT_ALL_UNIDADE_MEDIDA_SQL = "SELECT * FROM unidade_medida;";
    private static final String DELETE_UNIDADE_MEDIDA_SQL = "DELETE FROM unidade_medida WHERE id = ?;";
    private static final String UPDATE_UNIDADE_MEDIDA_SQL = "UPDATE unidade_medida SET descricao = ? WHERE id = ?;";
    private static final String COUNT_SQL = "SELECT count(1) FROM unidade_medida;";

    public int count() {
        return super.count(COUNT_SQL);
    }

    public UnidadeMedida insert(UnidadeMedida entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_UNIDADE_MEDIDA_SQL,
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

    public UnidadeMedida findById(long id) {
        UnidadeMedida entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_UNIDADE_MEDIDA_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                entidade = new UnidadeMedida(id);
                populateWithValues(entidade, rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public List<UnidadeMedida> selectAll() {
        List<UnidadeMedida> entidades = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_UNIDADE_MEDIDA_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long id = rs.getLong("id");
                var entidade = new UnidadeMedida(id);
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
        return super.delete(DELETE_UNIDADE_MEDIDA_SQL, id);
    }

    public void update(UnidadeMedida entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_UNIDADE_MEDIDA_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(UnidadeMedida entidade, PreparedStatement statement) throws SQLException {
        injectAllValues(entidade, statement);
        statement.setLong(2, entidade.getId());
    }

    private static void injectAllValues(UnidadeMedida entidade, PreparedStatement statement) throws SQLException {
        statement.setString(1, entidade.getDescricao());
    }

    private static void populateWithValues(UnidadeMedida entidade, ResultSet rs) throws SQLException {
        String descricao = rs.getString("descricao");
        entidade.setDescricao(descricao);
    }
}
