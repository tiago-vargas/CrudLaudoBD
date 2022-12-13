package br.com.frota.DAO;

import br.com.frota.model.ConsultaMedica;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaMedicaDAO extends GenericDAO {

    private static final String INSERT_CONSULTA_MEDICA_SQL =
            "INSERT INTO consulta_medica (dt_consulta, medico_id, paciente_id, nm_atendimento)"
                    + "VALUES (?, ?, ?, ?);";
    private static final String SELECT_CONSULTA_MEDICA_BY_ID =
            "SELECT * FROM consulta_medica WHERE id = ?";
    private static final String SELECT_ALL_CONSULTA_MEDICA =
            "SELECT * FROM consulta_medica;";
    private static final String DELETE_CONSULTA_MEDICA_SQL =
            "DELETE FROM consulta_medica WHERE id = ?;";
    private static final String UPDATE_CONSULTA_MEDICA_SQL =
            "UPDATE consulta_medica "
                    + "SET dt_consulta = ?, medico_id = ?, paciente_id = ?, nm_atendimento = ? "
                    + "WHERE id = ?;";
    private static final String TOTAL_SQL =
            "SELECT count(1) FROM consulta_medica;";

    public int count() {
        return super.count(TOTAL_SQL);
    }

    public ConsultaMedica insert(ConsultaMedica entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CONSULTA_MEDICA_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDate(1, entidade.getDtConsulta());
            preparedStatement.setLong(2, entidade.getMedicoId());
            preparedStatement.setLong(3, entidade.getPacienteId());
            preparedStatement.setString(4, entidade.getNmAtendimento());

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

    public ConsultaMedica findById(long id) {
        ConsultaMedica entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_CONSULTA_MEDICA_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Date dtConsulta = rs.getDate("dt_consulta");
                long medicoId = rs.getLong("medico_id");
                long pacienteId = rs.getLong("paciente_id");
                String nmAtendimento = rs.getString("nm_atendimento");

                entidade = new ConsultaMedica(id, dtConsulta, medicoId, pacienteId, nmAtendimento);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public List<ConsultaMedica> selectAll() {
        List<ConsultaMedica> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_CONSULTA_MEDICA)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                Date dtConsulta = rs.getDate("dt_consulta");
                long medicoId = rs.getLong("medico_id");
                long pacienteId = rs.getLong("paciente_id");
                String nmAtendimento = rs.getString("nm_atendimento");

                entidades.add(new ConsultaMedica(id, dtConsulta, medicoId, pacienteId, nmAtendimento));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidades;
    }

    public boolean delete(long id) throws SQLException {
        return super.delete(DELETE_CONSULTA_MEDICA_SQL, id);
    }

    public void update(ConsultaMedica entidade) throws SQLException {
        try (PreparedStatement statement = prepararSQL(UPDATE_CONSULTA_MEDICA_SQL)) {
            statement.setDate(1, entidade.getDtConsulta());
            statement.setLong(2, entidade.getMedicoId());
            statement.setLong(3, entidade.getPacienteId());
            statement.setString(4, entidade.getNmAtendimento());
            statement.setLong(5, entidade.getId());

            statement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
