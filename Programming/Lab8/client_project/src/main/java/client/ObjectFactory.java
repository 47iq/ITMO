package client;

import client.connection.ConnectionManager;
import client.connection.RequestSender;
import client.connection.ResponseReader;
import client.controllers.ControlManager;
import client.controllers.ControllerContext;
import client.reader.CommandReader;
import client.ticket.TicketBuilder;
import common.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.SocketChannel;

/**
 * Object factory used by client application
 */

public interface ObjectFactory {

    CommandReader getFileReader(ConnectionManager commandFactory, File file) throws FileNotFoundException;

    DefaultTicket getDefaultTicket();

    DefaultCoordinates getDefaultCoordinates();

    DefaultPerson getDefaultPerson();

    Request getRequest(RequestType type, String commandName);

    RequestSender getRequestSender(SocketChannel outputStream);

    ResponseReader getResponseReader(SocketChannel inputStream);

    User getUser(String login, String password);

    CommandReader getControllerCommandReader();

    Response getResponse(boolean successful, String message);

    TicketBuilder getTicketBuilder();

    UpdateData getDefaultUpdateData();

    UpdateData getUpdateData();

    ControllerContext getContext();

    ControlManager getControllerManager();
}
