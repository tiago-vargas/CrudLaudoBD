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
            "INSERT INTO consulta_medica (dt_consulta, medico_id, paciente_id, nm_atendimento) "
                    + "VALUES (?, ?, ?, ?);";
    private static final String SELECT_CONSULTA_MEDICA_BY_ID_SQL = "SELECT * FROM consulta_medica WHERE id = ?";
    private static final String SELECT_ALL_CONSULTA_MEDICA_SQL = "SELECT * FROM consulta_medica;";
    private static final String DELETE_CONSULTA_MEDICA_SQL = "DELETE FROM consulta_medica WHERE id = ?;";
    private static final String UPDATE_CONSULTA_MEDICA_SQL =
            "UPDATE consulta_medica "
                    + "SET dt_consulta = ?, medico_id = ?, paciente_id = ?, nm_atendimento = ? "
                    + "WHERE id = ?;";
    private static final String COUNT_SQL = "SELECT count(1) FROM consulta_medica;";

    public int count() {
        return super.count(COUNT_SQL);
    }

    public ConsultaMedica insert(ConsultaMedica entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_CONSULTA_MEDICA_SQL,
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

    public ConsultaMedica findById(long id) {
        ConsultaMedica entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_CONSULTA_MEDICA_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                entidade = new ConsultaMedica(id);
                populateWithValues(entidade, rs);
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

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_CONSULTA_MEDICA_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                var entidade = new ConsultaMedica(id);
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
        return super.delete(DELETE_CONSULTA_MEDICA_SQL, id);
    }

    public void update(ConsultaMedica entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_CONSULTA_MEDICA_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(ConsultaMedica entidade, PreparedStatement preparedStatement)
            throws SQLException {
        injectAllValues(entidade, preparedStatement);
        preparedStatement.setLong(5, entidade.getId());
    }

    private static void injectAllValues(ConsultaMedica entidade, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setDate(1, entidade.getDtConsulta());
        preparedStatement.setLong(2, entidade.getMedicoId());
        preparedStatement.setLong(3, entidade.getPacienteId());
        preparedStatement.setString(4, entidade.getNmAtendimento());
    }

    private static void populateWithValues(ConsultaMedica entidade, ResultSet rs) throws SQLException {
        Date dtConsulta = rs.getDate("dt_consulta");
        long medicoId = rs.getLong("medico_id");
        long pacienteId = rs.getLong("paciente_id");
        String nmAtendimento = rs.getString("nm_atendimento");

        entidade.setDtConsulta(dtConsulta);
        entidade.setMedicoId(medicoId);
        entidade.setPacienteId(pacienteId);
        entidade.setNmAtendimento(nmAtendimento);
    }
}
