package main;

import main.ticket.*;
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * Interface of object factory for server application
 */

public interface ServerObjectFactory {

    ServerTicket getServerTicket();

    DefaultCoordinates getCoordinates();

    DefaultPerson getPerson();

    /**
     * Getter for least coordinates possible
     * @return the least coordinates
     */

    Coordinates getLeastCoordinates();

    /**
     * Converter from some template ticket to ticket with id and creation time
     * @param ticket template ticket
     * @return real ticket
     */

    ServerTicket convertTicket(Ticket ticket);
}
