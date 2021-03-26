package server;

import common.*;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.datawork.TicketsDataBase;
import server.datawork.UsersDataBase;
import server.exceptions.ServerExceptionMessenger;
import server.messages.Messenger;
import server.ticket.ServerTicket;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;

/**
 * Interface of object factory for server application
 */

public interface ObjectFactory {

    ServerTicket getServerTicket();

    DefaultCoordinates getCoordinates();

    DefaultPerson getPerson();


    /**
     * Converter from some template ticket to ticket with id and creation time
     *
     * @param ticket template ticket
     * @return real ticket
     */

    ServerTicket convertTicket(Ticket ticket);

    Response getResponse(boolean successful, String message);

    Messenger getLocalMessenger(Locale locale);

    UsersDataBase getUsersData(Connection connection) throws SQLException;

    TicketsDataBase getTicketsData(Connection connection) throws SQLException;

    ServerExceptionMessenger getLocalErrMessenger(Locale locale);

    Visitor getCommandVisitor(String curArg, Ticket curTicket, CollectionManager collectionManager,
                              ObjectFactory factory, String login, UpdateData updateData);
}
