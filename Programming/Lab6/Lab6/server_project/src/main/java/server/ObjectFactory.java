package server;

import common.DefaultCoordinates;
import common.DefaultPerson;
import common.Response;
import common.Ticket;
import server.messages.Messenger;
import server.ticket.ServerTicket;

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
     * @param ticket template ticket
     * @return real ticket
     */

    ServerTicket convertTicket(Ticket ticket);

    Response getResponse(boolean successful, String message);

    Messenger getLocalMessenger(Locale locale);
}
