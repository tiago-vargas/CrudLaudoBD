package br.com.frota.DAO;

import br.com.frota.model.Laboratorio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LaboratorioDAO extends GenericDAO {

    private static final String INSERT_LABORATORIO_SQL =
            "INSERT INTO laboratorio (descricao, cnes, cnpj, crbm, nome_fantasia) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_LABORATORIO_BY_ID_SQL = "SELECT * FROM laboratorio WHERE id = ?";
    private static final String SELECT_ALL_LABORATORIO_SQL = "SELECT * FROM laboratorio;";
    private static final String DELETE_LABORATORIO_SQL = "DELETE FROM laboratorio WHERE id = ?;";
    private static final String UPDATE_LABORATORIO_SQL =
            "UPDATE laboratorio SET descricao = ?, cnes = ?, cnpj = ?, crbm = ?, nome_fantasia = ? WHERE id = ?;";
    private static final String TOTAL_SQL = "SELECT count(1) FROM laboratorio;";


    public int count() {
        return super.count(TOTAL_SQL);
    }

    public Laboratorio insert(Laboratorio entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_LABORATORIO_SQL,
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

    public Laboratorio findById(long id) {
        Laboratorio entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_LABORATORIO_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                entidade = new Laboratorio(id);
                populateWithValues(entidade, rs);
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
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long id = rs.getLong("id");
                var laboratorio = new Laboratorio(id);
                populateWithValues(laboratorio, rs);

                entidades.add(laboratorio);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidades;
    }

    public boolean delete(long id) throws SQLException {
        return super.delete(DELETE_LABORATORIO_SQL, id);
    }

    public void update(Laboratorio entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_LABORATORIO_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(Laboratorio entidade, PreparedStatement preparedStatement)
            throws SQLException {
        injectAllValues(entidade, preparedStatement);
        preparedStatement.setLong(6, entidade.getId());
    }

    private static void injectAllValues(Laboratorio entidade, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setString(1, entidade.getDescricao());
        preparedStatement.setString(2, entidade.getCnes());
        preparedStatement.setString(3, entidade.getCnpj());
        preparedStatement.setString(4, entidade.getCrbm());
        preparedStatement.setString(5, entidade.getNomeFantasia());
    }

    private static void populateWithValues(Laboratorio entidade, ResultSet rs) throws SQLException {
        String descricao = rs.getString("descricao");
        String cnes = rs.getString("cnes");
        String cnpj = rs.getString("cnpj");
        String crbm = rs.getString("crbm");
        String nomeFantasia = rs.getString("nome_fantasia");

        entidade.setDescricao(descricao);
        entidade.setCnes(cnes);
        entidade.setCnpj(cnpj);
        entidade.setCrbm(crbm);
        entidade.setNomeFantasia(nomeFantasia);
    }
}
