package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {


    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.execute("CREATE TABLE `testdb`.`users` (`id` BIGINT NOT NULL AUTO_INCREMENT, `name` VARCHAR(50) NOT NULL, `lastName` VARCHAR(45) NOT NULL, `age` INT(200) NOT NULL, PRIMARY KEY (`id`));");
            Util.getConnection().close();
        } catch (SQLException ignored) {

        }
    }

    public void dropUsersTable() {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.execute("DROP TABLE users");
            Util.getConnection().close();
        } catch (SQLException ignored) {

        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (final PreparedStatement stat = Util.getConnection().prepareStatement("insert into users (name, lastName, age) values ((?) ,(?) ,(?) )")) {
            stat.setString(1, name);
            stat.setString(2, lastName);
            stat.setString(3, String.valueOf(age));
            stat.executeUpdate();
            Util.getConnection().close();

        } catch (SQLException e) {


        }
    }

    public void removeUserById(long id) {
        try (final PreparedStatement stat = Util.getConnection().prepareStatement("DELETE FROM users WHERE id = (?)")) {
            stat.setString(1, String.valueOf(id));
            stat.executeUpdate();
            Util.getConnection().close();

        } catch (SQLException e) {

        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Statement stat = Util.getConnection().createStatement()) {
            ResultSet rs = stat.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                User user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                userList.add(user);
            }

            Util.getConnection().close();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return userList;

    }

    public void cleanUsersTable() {
        try (Statement stat = Util.getConnection().createStatement()) {
            stat.execute("DELETE FROM users");
            Util.getConnection().close();
        } catch (SQLException e) {

        }
    }
}

