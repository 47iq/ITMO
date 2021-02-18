package main;

import main.ticket.*;
import org.json.simple.JSONObject;

import java.util.Collection;

/**
 * Interface of manager.ticket factory, realization of fabric method pattern
 */

public interface ServerObjectFactory {
    Coordinates getCoordinates(double x, Integer y);
    Coordinates getCoordinates(JSONObject jsonCoordinates);
    Ticket getTicket(JSONObject jsonTicket);
    Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country);
    Person getPerson(JSONObject jsonPerson);
    Collection<Ticket> getTicketsCollection();
    Coordinates getLeastCoordinates();
    Ticket convertTicket(Ticket ticket);
}
