package server;

import common.*;
import org.apache.logging.log4j.LogManager;
import server.collection.CollectionManager;
import server.command_manager.CommandVisitor;
import server.command_manager.Visitor;
import server.datawork.*;
import server.exceptions.ServerExceptionMessenger;
import server.messages.*;
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

    /**
     * Method to localize response.
     * NOTE: also sets error handler in Main.
     *
     * @param locale local language
     * @return messenger
     */

    public Messenger getLocalMessenger(Locale locale) {
        Locale localeRu = new Locale("ru", "RU");
        if (locale.getLanguage().equals(localeRu.getLanguage())) {
            return new MessagesRU();
        } else if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            return new MessagesENG();
        } else {
            LogManager.getLogger().warn("Error. Can't get local message class. English messenger will be used.");
            return new MessagesENG();
        }
    }

    public ServerExceptionMessenger getLocalErrMessenger(Locale locale) {
        Locale localeRu = new Locale("ru", "RU");
        if (locale.getLanguage().equals(localeRu.getLanguage())) {
            return new ErrorMessengerRU();
        } else if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            return new ErrorMessengerENG();
        } else {
            LogManager.getLogger().warn("Error. Can't get local message class. English messenger will be used.");
            return new ErrorMessengerENG();
        }
    }

    public Visitor getCommandVisitor(String curArg, Ticket curTicket, CollectionManager collectionManager,
                                     ObjectFactory factory, String login, UpdateData updateData) {
        return new CommandVisitor(curArg, curTicket, collectionManager, factory, login, updateData);
    }

    private ServerExceptionMessenger getRUVisitor() {
        return new ErrorMessengerRU();
    }

    private ServerExceptionMessenger getENGVisitor() {
        return new ErrorMessengerENG();
    }


    public UsersDataBase getUsersData(Connection connection) throws SQLException {
        return new PostgresUsersDataBase(connection, getCryptoModule());
    }

    public CryptoModule getCryptoModule() {
        return new SHA384CryptoModule();
    }

    public TicketsDataBase getTicketsData(Connection connection) throws SQLException {
        return new PostgresTicketsDataBase(connection, this);
    }
}
