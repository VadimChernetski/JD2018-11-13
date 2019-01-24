package by.it.nickgrudnitsky.jd03_03.dao;

import by.it.nickgrudnitsky.jd03_02.CRUD.Connect;
import by.it.nickgrudnitsky.jd03_02.beans.Role;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoleDAO {
    public boolean create(Role role) throws SQLException {
        String sql = String.format("INSERT INTO `ngrudnitsky`.`roles` (`role`)"+
                " VALUES ('%s')", role.getRole()
        );
        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement()) {
            if (1 == statement.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS)) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    role.setId(generatedKeys.getLong(1));
                }
                return true;
            }
        }
        return false;
    }

    public boolean delete(Role role) throws SQLException {
        String sql = String.format(
                "DELETE FROM `roles` WHERE `roles`.`id` = %d",
                role.getId()
        );
        try (Connection connection = Connect.getConnection();

             Statement statement = connection.createStatement()) {
            return (1 == statement.executeUpdate(sql));
        }
    }

    public boolean update(Role role) throws SQLException {
        String sql = String.format(
                "UPDATE `roles` SET `role` = '%s'" +
                        " WHERE `roles`.`id` = %d",
                role.getRole(),
                role.getId()
        );
        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement()) {
            return (1 == statement.executeUpdate(sql));
        }
    }

    public Role read(long id) throws SQLException {
        String sql=String.format("SELECT `id`, `role` " +
                "FROM `roles` WHERE id=%d",id);
        try (Connection connection = Connect.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                //id ok
                String role=resultSet.getString("role");

                return new Role(role);
            }
            else
                return null;
        }

    }
}
