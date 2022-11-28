package br.com.frota.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.frota.model.Marca;

public class MarcaDAO extends ConexaoDB {

	private static final String INSERT_MARCA_SQL = "INSERT INTO marca (descricao, observacao) VALUES (?, ?);";
	private static final String SELECT_MARCA_BY_ID = "SELECT id, descricao, observacao FROM marca WHERE id = ?";
	private static final String SELECT_ALL_MARCA = "SELECT * FROM marca;";
	private static final String DELETE_MARCA_SQL = "DELETE FROM marca WHERE id = ?;";
	private static final String BUSCAR_POR_DESCRICAO_MARCA_SQL = "DELETE FROM marca WHERE descricao = ?;";
	private static final String UPDATE_MARCA_SQL = "UPDATE marca SET descricao = ?, observacao = ? WHERE id = ?;";
	private static final String TOTAL = "SELECT count(1) FROM marca;";

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

	public Marca insert(Marca entidade) {
		try (PreparedStatement preparedStatement = prepararSQL(INSERT_MARCA_SQL,
				java.sql.Statement.RETURN_GENERATED_KEYS)) {

			preparedStatement.setString(1, entidade.getDescricao());
			preparedStatement.setString(2, entidade.getObservacao());

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

	public Marca findByDecricao(String descricao) {
		Marca entidade = null;
		try (PreparedStatement preparedStatement = prepararSQL(BUSCAR_POR_DESCRICAO_MARCA_SQL)) {
			preparedStatement.setString(1, descricao);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				entidade = new Marca(rs.getLong("id"), rs.getString("descricao"), rs.getString("observacao"));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}

		return entidade;
	}

	public Marca findById(long id) {
		Marca entidade = null;
		try (PreparedStatement preparedStatement = prepararSQL(SELECT_MARCA_BY_ID)) {
			preparedStatement.setLong(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String descricao = rs.getString("descricao");
				String observacao = rs.getString("observacao");
				entidade = new Marca(id, descricao, observacao);
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return entidade;
	}

	public List<Marca> selectAllMarcas() {
		List<Marca> entidades = new ArrayList<>();
		try (PreparedStatement preparedStatement = prepararSQL(SELECT_ALL_MARCA)) {
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				long id = rs.getLong("id");
				String descricao = rs.getString("descricao");
				String observacao = rs.getString("observacao");
				entidades.add(new Marca(id, descricao, observacao));
			}
		} catch (SQLException e) {
			printSQLException(e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return entidades;
	}

	public boolean deleteMarca(int id) throws SQLException {
		try (PreparedStatement statement = prepararSQL(DELETE_MARCA_SQL)) {
			statement.setInt(1, id);

			return statement.executeUpdate() > 0;
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateMarca(Marca entidade) throws SQLException {
		try (PreparedStatement statement = prepararSQL(UPDATE_MARCA_SQL)) {
			statement.setString(1, entidade.getDescricao());
			statement.setString(2, entidade.getObservacao());
			statement.setLong(3, entidade.getId());

		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
