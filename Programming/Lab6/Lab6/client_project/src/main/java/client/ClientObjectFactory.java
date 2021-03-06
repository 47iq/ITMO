package client;

import client.connection.*;
import client.messages.MessagesENG;
import client.messages.MessagesRU;
import client.messages.Messenger;
import client.reader.CommandReader;
import client.reader.FileCommandReader;
import client.ticket.ClientTemplateCoordinates;
import client.ticket.ClientTemplatePerson;
import client.ticket.ClientTemplateTicket;
import common.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.channels.SocketChannel;
import java.util.Locale;

/**
 * Class for creation of objects for client application
 */

public class ClientObjectFactory implements ObjectFactory {

    private Locale locale;

    public ClientObjectFactory(Locale locale) {
        this.locale = locale;
    }

    public Coordinates getCoordinates(double x, Integer y) {
        return new ClientTemplateCoordinates(x, y);
    }

    public CommandReader getFileReader(RequestFactory commandFactory, File file) throws FileNotFoundException {
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
            return new MessagesRU();
        else if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage()))
            return new MessagesENG();
        else {
            System.err.println("Error. Can't get local message class. English messenger will be used.");
            return new MessagesENG();
        }
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
