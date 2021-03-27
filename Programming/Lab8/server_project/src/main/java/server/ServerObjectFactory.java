package server;

import common.*;
import org.apache.logging.log4j.LogManager;
import server.collection.CollectionManager;
import server.command_manager.CommandVisitor;
import server.command_manager.Visitor;
import server.datawork.*;
import server.ticket.ServerDefaultCoordinates;
import server.ticket.ServerDefaultPerson;
import server.ticket.ServerDefaultTicket;
import server.ticket.ServerTicket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Class that creates object for the server application
 */

public class ServerObjectFactory implements ObjectFactory {

    public ServerTicket convertTicket(Ticket ticket) {
        return ServerDefaultTicket.convert(ticket);
    }

    public Response getResponse(boolean s, String msg) {
        return new DefaultResponse(s, msg);
    }

    public DefaultCoordinates getCoordinates() {
        return new ServerDefaultCoordinates();
    }

    public ServerTicket getServerTicket() {
        return new ServerDefaultTicket();
    }

    public DefaultPerson getPerson() {
        return new ServerDefaultPerson();
    }

    public Visitor getCommandVisitor(String curArg, Ticket curTicket, CollectionManager collectionManager,
                                     ObjectFactory factory, String login, UpdateData updateData, UsersDataBase dataBase) {
        return new CommandVisitor(curArg, curTicket, collectionManager, factory, login, updateData, dataBase);
    }


    public UsersDataBase getUsersData(Connection connection) throws SQLException {
        return new PostgresUsersDataBase(connection, getCryptoModule(), getColorGenerator());
    }

    private ColorGenerator getColorGenerator() {
        return new DefaultColorGenerator();
    }

    public CryptoModule getCryptoModule() {
        return new SHA384CryptoModule();
    }

    public TicketsDataBase getTicketsData(Connection connection) throws SQLException {
        return new PostgresTicketsDataBase(connection, this);
    }
}
