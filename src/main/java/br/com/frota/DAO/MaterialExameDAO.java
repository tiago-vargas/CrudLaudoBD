package br.com.frota.DAO;

import br.com.frota.model.MaterialExame;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialExameDAO extends GenericDAO {

    private static final String INSERT_MATERIAL_EXAME_SQL =
            "INSERT INTO material_exame (material, observacao) VALUES (?, ?);";
    private static final String SELECT_MATERIAL_EXAME_BY_ID_SQL = "SELECT * FROM material_exame WHERE id = ?";
    private static final String SELECT_ALL_MATERIAL_EXAME_SQL = "SELECT * FROM material_exame;";
    private static final String DELETE_MATERIAL_EXAME_SQL = "DELETE FROM material_exame WHERE id = ?;";
    private static final String UPDATE_MATERIAL_EXAME_SQL =
            "UPDATE material_exame SET material = ?, observacao = ? WHERE id = ?;";
    private static final String TOTAL_SQL = "SELECT count(1) FROM material_exame;";

    public int count() {
        return super.count(TOTAL_SQL);
    }

    public MaterialExame insert(MaterialExame entidade) {
        try (PreparedStatement preparedStatement = prepararSQL(INSERT_MATERIAL_EXAME_SQL,
                java.sql.Statement.RETURN_GENERATED_KEYS)) {

            injectAllValues(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                entidade.setId(rs.getLong(1));
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public MaterialExame findById(long id) {
        MaterialExame entidade = null;

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_MATERIAL_EXAME_BY_ID_SQL)) {
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                entidade = new MaterialExame(id);
                populateWithValues(entidade, rs);
            }
        } catch (SQLException e) {
            printSQLException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return entidade;
    }

    public List<MaterialExame> selectAll() {
        List<MaterialExame> entidades = new ArrayList<>();

        try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MATERIAL_EXAME_SQL)) {
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

            while (rs.next()) {
                long id = rs.getLong("id");
                var entidade = new MaterialExame(id);
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
        return super.delete(DELETE_MATERIAL_EXAME_SQL, id);
    }

    public void update(MaterialExame entidade) throws SQLException {
        try (PreparedStatement preparedStatement = prepararSQL(UPDATE_MATERIAL_EXAME_SQL)) {
            injectAllValuesAndId(entidade, preparedStatement);

            preparedStatement.executeUpdate();
            preparedStatement.getConnection().close();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void injectAllValuesAndId(MaterialExame entidade, PreparedStatement preparedStatement) throws SQLException {
        injectAllValues(entidade, preparedStatement);
        preparedStatement.setLong(3, entidade.getId());
    }

    private static void injectAllValues(MaterialExame entidade, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entidade.getMaterial());
        preparedStatement.setString(2, entidade.getObservacao());
    }

    private static void populateWithValues(MaterialExame entidade, ResultSet rs) throws SQLException {
        String material = rs.getString("material");
        String observacao = rs.getString("observacao");

        entidade.setMaterial(material);
        entidade.setObservacao(observacao);
    }
}
