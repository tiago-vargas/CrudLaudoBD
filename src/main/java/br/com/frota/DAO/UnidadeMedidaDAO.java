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
    private static final String TOTAL_SQL = "SELECT count(1) FROM unidade_medida;";

    public int count() {
        return super.count(TOTAL_SQL);
    }

    public UnidadeMedida insert(UnidadeMedida entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_UNIDADE_MEDIDA_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getDescricao());

            preparedStatement.executeUpdate();

            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()) {
                entidade.setId(result.getLong(1));
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

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                entidade = new UnidadeMedida(id, descricao);
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

            while (rs.next()) {
                long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                entidades.add(new UnidadeMedida(id, descricao));
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
        try (PreparedStatement statement = prepararSQL(UPDATE_UNIDADE_MEDIDA_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setLong(2, entidade.getId());

            statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
