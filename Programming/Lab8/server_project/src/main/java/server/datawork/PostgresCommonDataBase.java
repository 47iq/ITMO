package server.datawork;

import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PostgresCommonDataBase implements CommonDataBase{

    private final Connection connection;
    private final Statement statement;
    private Map<Integer, String> objectMap;

    public PostgresCommonDataBase(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        objectMap = new HashMap<>();
        create();
    }

    private void create() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS common " +
                "(ticket_id int primary key references tickets (id) ON DELETE CASCADE, login text references users (login))";
        statement.execute(createTableSQL);
        String query = " SELECT * FROM common";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            int ticket_id = resultSet.getInt(1);
            String user = resultSet.getString(2);
            objectMap.put(ticket_id, user);
        }
    }

    @Override
    public String getOwner(int ticketId) {
        return objectMap.get(ticketId);
    }

    @Override
    public void add(int id, String owner) throws SQLException {
        String sql = "INSERT INTO common (ticket_id, login) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, owner);
        preparedStatement.execute();
        objectMap.put(id, owner);
    }
}
