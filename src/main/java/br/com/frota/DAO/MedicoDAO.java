package br.com.frota.DAO;

import br.com.frota.model.Especialidade;
import br.com.frota.model.Medico;
import br.com.frota.model.MedicoHasEspecialidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoDAO extends GenericDAO {

    private static final String INSERT_MEDICO_SQL = "INSERT INTO medico (crm, nome) VALUES (?, ?);";
    private static final String SELECT_MEDICO_BY_ID_SQL = "SELECT id, crm, nome FROM medico WHERE id = ?";
    private static final String SELECT_ALL_MEDICO_SQL = "SELECT * FROM medico;";
    private static final String DELETE_MEDICO_SQL = "DELETE FROM medico WHERE id = ?;";
    private static final String UPDATE_MEDICO_SQL = "UPDATE medico SET crm = ?, nome = ? WHERE id = ?;";
    private static final String COUNT_SQL = "SELECT count(1) FROM medico;";
    private static final String INSERT_ESPECIALIDADE_SQL =
            "INSERT INTO medico_has_especialidade (medico_id, especialidade_id) VALUES (?, ?);";
    private static final String SELECT_ESPECIALIDADE_OF_MEDICO_SQL =
            "SELECT especialidade_id FROM medico_has_especialidade WHERE medico_id = ?";

    public int count() {
        return super.count(COUNT_SQL);
    }

    public Medico insert(Medico entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MEDICO_SQL,
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

    public MedicoHasEspecialidade insertEspecialidade(long medicoId, long especialidadeId) {
        MedicoHasEspecialidade medicoHasEspecialidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(INSERT_ESPECIALIDADE_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setLong(1, medicoId);
            preparedStatement.setLong(2, especialidadeId);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                medicoId = rs.getLong("medico_id");
                especialidadeId = rs.getLong("especialidade_id");
                medicoHasEspecialidade = new MedicoHasEspecialidade(medicoId, especialidadeId);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return medicoHasEspecialidade;
    }

    public Medico findById(long id) {
        Medico entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDICO_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                entidade = new Medico(id);
                populateWithValues(entidade, rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public List<Medico> selectAll() {
        List<Medico> entidades = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MEDICO_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long id = rs.getLong("id");
                var entidade = new Medico(id);
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

    public List<Especialidade> selectEspecialidadeOfMedico(long medicoId) {
        List<Especialidade> especialidades = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ESPECIALIDADE_OF_MEDICO_SQL)) {
            preparedStatement.setLong(1, medicoId);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long especialidadeId = rs.getLong("especialidade_id");
                var especialidadeDao = new EspecialidadeDAO();
                var especialidade = especialidadeDao.findById(especialidadeId);

                especialidades.add(especialidade);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return especialidades;
    }

    public boolean delete(long id) throws SQLException {
        new MedicoHasEspecialidadeDAO().deleteRowsOfMedico(id);
        return super.delete(DELETE_MEDICO_SQL, id);
    }

    public void update(Medico entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_MEDICO_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(Medico entidade, PreparedStatement statement) throws SQLException {
        injectAllValues(entidade, statement);

        statement.setLong(3, entidade.getId());
    }

    private static void injectAllValues(Medico entidade, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entidade.getCrm());
        preparedStatement.setString(2, entidade.getNome());
    }

    static void populateWithValues(Medico entidade, ResultSet rs) throws SQLException {
        String crm = rs.getString("crm");
        String nome = rs.getString("nome");

        entidade.setCrm(crm);
        entidade.setNome(nome);
    }

    public void removeEspecialidade(long medicoId, long especialidadeId) throws SQLException {
        new MedicoHasEspecialidadeDAO().delete(medicoId, especialidadeId);
    }
}
