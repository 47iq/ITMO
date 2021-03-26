package server.datawork;

import common.User;

import java.sql.SQLException;

public interface UsersDataBase {
    void add(User user) throws SQLException;

    void isAdmin(User user);

    boolean isValid(User user);

    boolean isPresent(String login);
}
