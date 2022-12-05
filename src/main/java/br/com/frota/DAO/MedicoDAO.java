package br.com.frota.DAO;

import br.com.frota.model.Medico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO extends ConexaoDB {

    private static final String INSERT_MEDICO_SQL = "INSERT INTO medico (crm, nome) VALUES (?, ?);";
    private static final String SELECT_MEDICO_BY_ID = "SELECT id, crm, nome FROM medico WHERE id = ?";
    private static final String SELECT_ALL_MEDICO = "SELECT * FROM medico;";
    private static final String DELETE_MEDICO_SQL = "DELETE FROM medico WHERE id = ?;";
    private static final String BUSCAR_POR_CRM_MEDICO_SQL = "DELETE FROM medico WHERE crm = ?;";
    private static final String UPDATE_MEDICO_SQL = "UPDATE medico SET crm = ?, nome = ? WHERE id = ?;";
    private static final String TOTAL = "SELECT count(1) FROM medico;";

    public Integer count() {
        Integer count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(TOTAL)) {
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

    public Medico insert(Medico entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDICO_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entidade.getCrm());
            preparedStatement.setString(2, entidade.getNome());

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

    public Medico findByCrm(String crm) {
        Medico entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(BUSCAR_POR_CRM_MEDICO_SQL)) {
            preparedStatement.setString(1, crm);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                entidade = new Medico(rs.getLong("id"), rs.getString("crm"), rs.getString("nome"));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public Medico findById(long id) {
        Medico entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDICO_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String crm = rs.getString("crm");
                String nome = rs.getString("nome");
                entidade = new Medico(id, crm, nome);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Medico> selectAllMedicos() {
        List<Medico> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MEDICO)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                String crm = rs.getString("crm");
                String nome = rs.getString("nome");
                entidades.add(new Medico(id, crm, nome));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean deleteMedico(int id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MEDICO_SQL)) {
            statement.setInt(1, id);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateMedico(Medico entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_MEDICO_SQL)) {
            statement.setString(1, entidade.getCrm());
            statement.setString(2, entidade.getNome());
            statement.setLong(3, entidade.getId());

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
