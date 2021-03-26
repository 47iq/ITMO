package server.datawork;

import common.User;
import org.apache.logging.log4j.LogManager;

import java.sql.*;

public class PostgresUsersDataBase implements UsersDataBase {

    private final Connection connection;

    private final Statement statement;

    private final CryptoModule cryptoModule;

    public PostgresUsersDataBase(Connection connection, CryptoModule crypto) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.cryptoModule = crypto;
        create();
    }

    private void create() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS  users " +
                "(login TEXT primary key not null, " +
                " password TEXT)";
        statement.execute(createTableSQL);
    }

    public void add(User user) throws SQLException {
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, cryptoModule.hash(user.getPassword()));
        preparedStatement.execute();
    }

    public void isAdmin(User user) {

    }

    public boolean isValid(User user) {
        try {
            String password = cryptoModule.hash(user.getPassword());
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = '" + user.getLogin() + "'");
            while (rs.next())
                if (password.equals(rs.getString(2)))
                    return true;
            return false;
        } catch (SQLException e) {
            LogManager.getLogger().error("Can't get user from database.");
            return false;
        }
    }

    public boolean isPresent(String username) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = '" + username + "'");
            return rs.next();
        } catch (SQLException e) {
            LogManager.getLogger().error("Can't get user from database.");
            return false;
        }
    }
}
