package br.com.frota.DAO;

import br.com.frota.model.Especialidade;
import br.com.frota.model.Medico;
import br.com.frota.model.MedicoHasEspecialidade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EspecialidadeDAO extends GenericDAO {

    private static final String INSERT_ESPECIALIDADE_SQL =
            "INSERT INTO especialidade (descricao, observacao) VALUES (?, ?);";
    private static final String SELECT_ESPECIALIDADE_BY_ID_SQL = "SELECT * FROM especialidade WHERE id = ?";
    private static final String SELECT_ALL_ESPECIALIDADE_SQL = "SELECT * FROM especialidade;";
    private static final String SELECT_MEDICO_WITH_ESPECIALIDADE_SQL =
            "SELECT medico_id FROM medico_has_especialidade WHERE especialidade_id = ?";
    private static final String DELETE_ESPECIALIDADE_SQL = "DELETE FROM especialidade WHERE id = ?;";
    private static final String UPDATE_ESPECIALIDADE_SQL =
            "UPDATE especialidade SET descricao = ?, observacao = ? WHERE id = ?;";
    private static final String TOTAL_SQL = "SELECT count(1) FROM especialidade;";

    public int count() {
        return super.count(TOTAL_SQL);
    }

    public Especialidade insert(Especialidade entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_ESPECIALIDADE_SQL,
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

    public Especialidade findById(long id) {
        Especialidade entidade = null;
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ESPECIALIDADE_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                entidade = new Especialidade(id);
                populateWithValues(entidade, rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidade;
    }

    public List<Especialidade> selectAll() {
        List<Especialidade> entidades = new ArrayList<>();
        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_ESPECIALIDADE_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("id");
                var especialidade = new Especialidade(id);

                populateWithValues(especialidade, rs);

                entidades.add(especialidade);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return entidades;
    }

    public boolean delete(long id) throws SQLException {
        new MedicoHasEspecialidadeDAO().deleteRowsOfEspecialidade(id);
        return super.delete(DELETE_ESPECIALIDADE_SQL, id);
    }

    public void update(Especialidade entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_ESPECIALIDADE_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(Especialidade entidade, PreparedStatement preparedStatement)
            throws SQLException {
        injectAllValues(entidade, preparedStatement);
        preparedStatement.setLong(3, entidade.getId());
    }

    private static void injectAllValues(Especialidade entidade, PreparedStatement preparedStatement)
            throws SQLException {
        preparedStatement.setString(1, entidade.getDescricao());
        preparedStatement.setString(2, entidade.getObservacao());
    }

    static void populateWithValues(Especialidade entidade, ResultSet rs) throws SQLException {
        String descricao = rs.getString("descricao");
        String observacao = rs.getString("observacao");

        entidade.setDescricao(descricao);
        entidade.setObservacao(observacao);
    }

    public List<Medico> selectMedicosWithEspecialidade(long especialidadeId) {
        List<Medico> medicos = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MEDICO_WITH_ESPECIALIDADE_SQL)) {
            preparedStatement.setLong(1, especialidadeId);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                long medicoId = rs.getLong("especialidade_id");
                var medicoDAO = new MedicoDAO();
                var medico = medicoDAO.findById(medicoId);

                medicos.add(medico);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return medicos;
    }
}
