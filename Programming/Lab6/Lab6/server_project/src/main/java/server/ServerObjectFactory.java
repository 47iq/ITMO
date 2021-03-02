package server;

import common.*;
import org.apache.logging.log4j.LogManager;
import server.messages.MessagesENG;
import server.messages.MessagesRU;
import server.messages.Messenger;
import server.ticket.ServerDefaultCoordinates;
import server.ticket.ServerDefaultPerson;
import server.ticket.ServerDefaultTicket;
import server.ticket.ServerTicket;

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

    public Messenger getLocalMessenger(Locale locale) {
        Locale localeRu = new Locale("ru", "RU");
        if (locale.getLanguage().equals(localeRu.getLanguage()))
            return new MessagesRU();
        else if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage()))
            return new MessagesENG();
        else {
            LogManager.getLogger().warn("Error. Can't get local message class. English messenger will be used.");
            return new MessagesENG();
        }
    }
}
