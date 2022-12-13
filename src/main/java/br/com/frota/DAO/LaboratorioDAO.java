package br.com.frota.DAO;

import br.com.frota.model.Laboratorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaboratorioDAO extends ConexaoDB {

    private static final String INSERT_LABORATORIO_SQL =
            "INSERT INTO laboratorio (descricao, cnes, cnpj, crbm, nome_fantasia) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_LABORATORIO_BY_ID_SQL = "SELECT * FROM laboratorio WHERE id = ?";
    private static final String SELECT_ALL_LABORATORIO_SQL = "SELECT * FROM laboratorio;";
    private static final String DELETE_LABORATORIO_SQL = "DELETE FROM laboratorio WHERE id = ?;";
    private static final String UPDATE_LABORATORIO_SQL =
            "UPDATE laboratorio SET descricao = ?, cnes = ?, cnpj = ?, crbm = ?, nome_fantasia = ? WHERE id = ?;";
    private static final String TOTAL_SQL = "SELECT count(1) FROM laboratorio;";


    public int count() {
        int count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(TOTAL_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                count = rs.getInt("count");
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return count;
    }

    public Laboratorio insert(Laboratorio entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_LABORATORIO_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getDescricao());
            preparedStatement.setString(2, entidade.getCnes());
            preparedStatement.setString(3, entidade.getCnpj());
            preparedStatement.setString(4, entidade.getCrbm());
            preparedStatement.setString(5, entidade.getNomeFantasia());

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

    public Laboratorio findById(long id) {
        Laboratorio entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_LABORATORIO_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String descricao = rs.getString("descricao");
                String cnes = rs.getString("cnes");
                String cnpj = rs.getString("cnpj");
                String crbm = rs.getString("crbm");
                String nomeFantasia = rs.getString("nome_fantasia");
                entidade = new Laboratorio(id, descricao, cnes, cnpj, crbm, nomeFantasia);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Laboratorio> selectAll() {
        List<Laboratorio> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_LABORATORIO_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String descricao = rs.getString("descricao");
                String cnes = rs.getString("cnes");
                String cnpj = rs.getString("cnpj");
                String crbm = rs.getString("crbm");
                String nomeFantasia = rs.getString("nome_fantasia");
                entidades.add(new Laboratorio(id, descricao, cnes, cnpj, crbm, nomeFantasia));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean delete(long id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_LABORATORIO_SQL)) {
            statement.setLong(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Laboratorio entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_LABORATORIO_SQL)) {
            statement.setString(1, entidade.getDescricao());
            statement.setString(2, entidade.getCnes());
            statement.setString(3, entidade.getCnpj());
            statement.setString(4, entidade.getCrbm());
            statement.setString(5, entidade.getNomeFantasia());
            statement.setLong(6, entidade.getId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
