package br.com.frota.DAO;

import br.com.frota.model.MedicoHasEspecialidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoHasEspecialidadeDAO extends GenericDAO {

    private static final String INSERT_MEDICO_HAS_ESPECIALIDADE_SQL =
            "INSERT INTO medico_has_especialidade (medico_id, especialidade_id) "
                    + "VALUES (?, ?);";
    private static final String DELETE_MEDICO_HAS_ESPECIALIDADE_SQL =
            "DELETE FROM medico_has_especialidade WHERE medico_id = ?, especialidade_id = ?";
    private static final String DELETE_ALL_ROWS_OF_MEDICO_SQL =
            "DELETE FROM medico_has_especialidade WHERE medico_id = ?;";
    private static final String DELETE_ALL_ROWS_OF_ESPECIALIDADE_SQL =
            "DELETE FROM medico_has_especialidade WHERE especialidade_id = ?;";
    private static final String COUNT_SQL = "SELECT count(1) FROM medico_has_especialidade;";

    public void deleteRowsOfMedico(long medicoId) throws SQLException {
        super.delete(DELETE_ALL_ROWS_OF_MEDICO_SQL, medicoId);
    }

    public void deleteRowsOfEspecialidade(long especialidadeId) throws SQLException {
        super.delete(DELETE_ALL_ROWS_OF_ESPECIALIDADE_SQL, especialidadeId);
    }

    public boolean delete(long medicoId, long especialidadeId) throws SQLException {
        try (PreparedStatement statement = prepararSQL(DELETE_MEDICO_HAS_ESPECIALIDADE_SQL)) {
            statement.setLong(1, medicoId);
            statement.setLong(2, especialidadeId);

            return statement.executeUpdate() > 0;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public int count() {
        return super.count(COUNT_SQL);
    }

    public MedicoHasEspecialidade insert(MedicoHasEspecialidade entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDICO_HAS_ESPECIALIDADE_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            injectAllValues(entidade, preparedStatement);

            preparedStatement.executeUpdate();

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

    private static void injectAllValues(MedicoHasEspecialidade entidade, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setLong(1, entidade.getMedicoId());
        preparedStatement.setLong(2, entidade.getEspecialidadeId());
    }
}
