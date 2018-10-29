package dao;

import connection.ConnectionPool;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import model.Business;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BusinessDao {

    private static final String CREATE = "insert into ibank.business(name, unp) values(?, ?)";

    public Business create(Business business) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, business.getName());
            statement.setInt(2, business.getUnp());
            statement.executeUpdate();
            ResultSet created = statement.getGeneratedKeys();
            if (created.next()) {
                business.setId(created.getLong("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return business;
    }

    private static final String GET_BY_ID = "select * from ibank.business where id = ?";

    public Business getById(Long id) {
        Business business = null;
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET_BY_ID)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                business = Business.builder()
                        .id(resultSet.getLong("id"))
                        .name(resultSet.getString("name"))
                        .unp(resultSet.getInt("unp"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return business;
    }

    private static final BusinessDao INSTANCE = new BusinessDao();

    public static BusinessDao getInstance() {
        return INSTANCE;
    }
}
