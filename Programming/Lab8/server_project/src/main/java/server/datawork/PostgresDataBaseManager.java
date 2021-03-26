package server.datawork;

import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;

import java.sql.Connection;
import java.sql.DriverManager;


public class PostgresDataBaseManager implements DataBaseManager {

    private UsersDataBase users;

    private TicketsDataBase tickets;

    private Connection connection;

    private final ObjectFactory factory;

    public PostgresDataBaseManager(ObjectFactory factory) {
        this.factory = factory;
    }

    public void start(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            users = factory.getUsersData(connection);
            tickets = factory.getTicketsData(connection);
        } catch (Exception e) {
            LogManager.getLogger().error("Error. Can't connect to data base: exception {}", e.getClass());
            System.exit(1);
        }
        if (connection != null)
            LogManager.getLogger().info("Successfully connected to the data base.");
        else
            LogManager.getLogger().error("Error. Can't connect to data base.");
    }

    public TicketsDataBase getTicketsData() {
        return tickets;
    }

    public UsersDataBase getUsersData() {
        return users;
    }
}
