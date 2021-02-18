package main;

import main.ticket.*;
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * Interface of manager.ticket factory, realization of fabric method pattern
 */

public interface ServerObjectFactory {
    Ticket getTicket(Object id, Object time, String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person);
    Coordinates getCoordinates(double x, Integer y);
    Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country);
    Coordinates getLeastCoordinates();
    Ticket convertTicket(Ticket ticket);
}
