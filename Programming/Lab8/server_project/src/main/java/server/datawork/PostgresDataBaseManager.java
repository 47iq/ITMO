package server.datawork;

import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;

import java.sql.Connection;
import java.sql.DriverManager;


public class PostgresDataBaseManager implements DataBaseManager {

    private UsersDataBase users;

    private TicketsDataBase tickets;

    private CommonDataBase common;

    private Connection connection;

    private final ObjectFactory factory;

    public PostgresDataBaseManager(ObjectFactory factory) {
        this.factory = factory;
    }

    @Override
    public void start(String url, String user, String password) {
        try {
            connection = DriverManager.getConnection(url, user, password);
            users = factory.getUsersData(connection);
            tickets = factory.getTicketsData(connection);
            common = factory.getCommonData(connection);
            tickets.setCommonDataBase(common);
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.getLogger().error("Error. Can't connect to data base: exception {}", e.getClass());
            System.exit(1);
        }
        if (connection != null)
            LogManager.getLogger().info("Successfully connected to the data base.");
        else
            LogManager.getLogger().error("Error. Can't connect to data base.");
    }

    @Override
    public TicketsDataBase getTicketsData() {
        return tickets;
    }

    @Override
    public UsersDataBase getUsersData() {
        return users;
    }
}
