package br.com.frota.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GenericDAO extends ConexaoDB {
    public int count(String sql) {
        int count = 0;
        try (PreparedStatement preparedStatement = prepararSQL(sql)) {
            ResultSet rs = preparedStatement.executeQuery();
            preparedStatement.getConnection().close();

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

    public boolean delete(String sql, long id) throws SQLException {
        try (PreparedStatement statement = prepararSQL(sql)) {
            statement.setLong(1, id);

            boolean hasDeleted = statement.executeUpdate() > 0;
            statement.getConnection().close();
            return hasDeleted;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
