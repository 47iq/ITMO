package client;

import client.connection.*;
import client.exceptions.LocalMessengerException;
import client.messages.*;
import client.reader.CommandReader;
import client.reader.FileCommandReader;
import client.ticket.ClientTemplateCoordinates;
import client.ticket.ClientTemplatePerson;
import client.ticket.ClientTemplateTicket;
import common.*;
import client.exceptions.ClientExceptionMessenger;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.SocketChannel;
import java.util.Locale;

/**
 * Class for creation of objects for client application
 */

public class ClientObjectFactory implements ObjectFactory {

    private final Locale locale;

    public ClientObjectFactory(Locale locale) {
        this.locale = locale;
    }


    public CommandReader getFileReader(ConnectionManager commandFactory, File file) throws FileNotFoundException {
        return new FileCommandReader(commandFactory, file, this);
    }

    public DefaultTicket getDefaultTicket() {
        return new ClientTemplateTicket();
    }

    public DefaultCoordinates getDefaultCoordinates() {
        return new ClientTemplateCoordinates();
    }

    public DefaultPerson getDefaultPerson() {
        return new ClientTemplatePerson();
    }

    public Request getRequest(RequestType type, String commandName) {
            return  new DefaultRequest(type, commandName, getLocale());
    }

    public RequestSender getRequestSender(SocketChannel channel) {
        return new DefaultRequestSender(channel);
    }

    public ResponseReader getResponseReader(SocketChannel channel) {
        return new DefaultResponseReader(channel);
    }

    public Messenger getLocalMessenger() {
        Locale localeRu = new Locale("ru", "RU");
        if (locale.getLanguage().equals(localeRu.getLanguage()))
            return new MessengerRU();
        else if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage()))
            return new MessengerENG();
        else {
            Main.getErr().println(new LocalMessengerException().accept(Main.getExceptionMessenger()));
            return new MessengerENG();
        }
    }

    public ClientExceptionMessenger getLocalErrorHandler() {
        Locale localeRu = new Locale("ru", "RU");
        if (locale.getLanguage().equals(localeRu.getLanguage()))
            return new ExceptionMessengerRU();
        else
            return new ExceptionMessengerENG();
    }

    public Locale getLocale() {
        return locale;
    }

    public User getUser(String login, String password) {
        return new DefaultUser(login, password);
    }
}
