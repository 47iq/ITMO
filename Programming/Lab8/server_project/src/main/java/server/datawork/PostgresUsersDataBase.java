package server.datawork;

import common.User;
import org.apache.logging.log4j.LogManager;

import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PostgresUsersDataBase implements UsersDataBase {

    private final Connection connection;

    private final Statement statement;

    private final CryptoModule cryptoModule;

    private final ColorGenerator colorGenerator;

    private final Map<String, Color> colorCache;

    public PostgresUsersDataBase(Connection connection, CryptoModule crypto, ColorGenerator colorGenerator) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.cryptoModule = crypto;
        this.colorGenerator = colorGenerator;
        colorCache = new HashMap<>();
        create();
    }

    private void create() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS  users " +
                "(login TEXT primary key not null, " +
                " password TEXT, red int, green int, blue int)";
        statement.execute(createTableSQL);
        String query = " SELECT * FROM users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            String login = resultSet.getString(1);
            int red = resultSet.getInt(3);
            int green = resultSet.getInt(4);
            int blue = resultSet.getInt(5);
            Color color = new Color(red, green, blue);
            colorCache.put(login, color);
        }
    }

    @Override
    public void add(User user) throws SQLException {
        String sql = "INSERT INTO users (login, password, red, green, blue) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getLogin());
        preparedStatement.setString(2, cryptoModule.hash(user.getPassword()));
        Color color = getNewColor(user.getLogin());
        preparedStatement.setInt(3, color.getRed());
        preparedStatement.setInt(4, color.getGreen());
        preparedStatement.setInt(5, color.getBlue());
        preparedStatement.execute();
    }

    @Override
    public void isAdmin(User user) {

    }

    @Override
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

    @Override
    public boolean isPresent(String username) {
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM users WHERE login = '" + username + "'");
            return rs.next();
        } catch (SQLException e) {
            LogManager.getLogger().error("Can't get user from database.");
            return false;
        }
    }

    private void occupyColor(String user, Color color) {
        colorCache.put(user, color);
        colorGenerator.addColor(color);
    }

    private void freeColor(String user) {
        Color color = colorCache.get(user);
        colorCache.remove(user);
        colorGenerator.freeColor(color);
    }

    private Color getUserColor(User user) {
        return colorCache.get(user.getLogin());
    }

    @Override
    public Color getNewColor(String user) throws SQLException {
        Color color = colorGenerator.generateColor();
        String sql = "UPDATE users SET red = ?, green = ?, blue = ? WHERE login = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, color.getRed());
        preparedStatement.setInt(2, color.getGreen());
        preparedStatement.setInt(3, color.getBlue());
        preparedStatement.setString(4, user);
        preparedStatement.execute();
        freeColor(user);
        occupyColor(user, color);
        return color;
    }

    @Override
    public Map<String, Color> getUserColors() {
        return colorCache;
    }
}
