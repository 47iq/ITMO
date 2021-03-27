package server.datawork;

import common.User;

import java.awt.*;
import java.sql.SQLException;
import java.util.Map;

public interface UsersDataBase {
    void add(User user) throws SQLException;

    void isAdmin(User user);

    boolean isValid(User user);

    boolean isPresent(String login);

    Map<String, Color> getUserColors();

    Color getNewColor(String user) throws SQLException;
}
