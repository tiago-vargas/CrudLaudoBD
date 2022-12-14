package br.com.frota.DAO;

import br.com.frota.model.Paciente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

public class PacienteDAO extends GenericDAO {

    private static final String INSERT_PACIENTE_SQL = "INSERT INTO paciente (nome, dt_nascimento) VALUES (?, ?);";
    private static final String SELECT_PACIENTE_BY_ID = "SELECT * FROM paciente WHERE id = ?";
    private static final String SELECT_ALL_PACIENTE_SQL = "SELECT * FROM paciente;";
    private static final String DELETE_PACIENTE_SQL = "DELETE FROM paciente WHERE id = ?;";
    private static final String UPDATE_PACIENTE_SQL = "UPDATE paciente SET nome = ?, dt_nascimento = ? WHERE id = ?;";
    private static final String COUNT_SQL = "SELECT count(1) FROM paciente;";

    public int count() {
        return super.count(COUNT_SQL);
    }

    public Paciente insert(Paciente entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_PACIENTE_SQL,
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

    public Paciente findById(long id) {
        Paciente entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_PACIENTE_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                entidade = new Paciente(id);
                populateWithValues(entidade, rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public List<Paciente> selectAll() {
        List<Paciente> entidades = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_PACIENTE_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long id = rs.getLong("id");
                var entidade = new Paciente(id);
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
        return super.delete(DELETE_PACIENTE_SQL, id);
    }

    public void update(Paciente entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_PACIENTE_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(Paciente entidade, PreparedStatement preparedStatement) throws SQLException {
        injectAllValues(entidade, preparedStatement);
        preparedStatement.setLong(3, entidade.getId());
    }

    private static void injectAllValues(Paciente entidade, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entidade.getNome());
        preparedStatement.setDate(2, entidade.getDtNascimento());
    }

    private static void populateWithValues(Paciente entidade, ResultSet rs) throws SQLException {
        String nome = rs.getString("nome");
        Date dtNascimento = rs.getDate("dt_nascimento");

        entidade.setNome(nome);
        entidade.setDtNascimento(dtNascimento);
    }
}
