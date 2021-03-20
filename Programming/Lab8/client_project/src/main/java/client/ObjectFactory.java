package client;

import client.connection.ConnectionManager;
import client.connection.RequestSender;
import client.connection.ResponseReader;
import client.exceptions.ClientExceptionMessenger;
import client.messages.Messenger;
import client.reader.CommandReader;
import common.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.SocketChannel;
import java.util.Locale;

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

    Locale getLocale();

    Messenger getLocalMessenger();

    User getUser(String login, String password);

    ClientExceptionMessenger getLocalErrorHandler();

    CommandReader getControllerCommandReader();

    Response getResponse(boolean successful, String message);
}
