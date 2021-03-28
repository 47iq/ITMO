package server.datawork;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

public class PostgresCommonDataBase implements CommonDataBase{

    private final Connection connection;
    private final Statement statement;
    private Map<Integer, String> objectMap;

    public PostgresCommonDataBase(Connection connection) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        create();
    }

    private void create() throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS common " +
                "(ticket_id int primary key references tickets (id), user_id text references users (login))";
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
}
