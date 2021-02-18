package main;

import main.ticket.*;
import org.json.simple.JSONObject;
import org.omg.CORBA.OBJ_ADAPTER;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.PriorityQueue;

public class DefaultServerObjectFactory implements ServerObjectFactory {

    public Ticket convertTicket(Ticket ticket) {
        return new ServerDefaultTicket(ticket);
    }

    public Coordinates getCoordinates(double x, Integer y) {
        return new ServerDefaultCoordinates(x, y);
    }

    public Ticket getTicket(Object id, Object time, String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person) {
        return new ServerDefaultTicket(id, time, name, coordinates, price, discount, refundable, type, person);
    }

    public Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        return new ServerDefaultPerson(weight, eyesColor, hairColor, country);
    }

    public Collection<Ticket> getTicketsCollection() {
        return new PriorityQueue<>();
    }

    public Coordinates getLeastCoordinates() {
        return new ServerDefaultCoordinates(-171, -235);
    }
}
