package main;

import main.ticket.*;
import org.json.simple.JSONObject;

import java.util.Collection;
import java.util.PriorityQueue;

public class DefaultServerObjectFactory implements ServerObjectFactory {

    public Ticket getTicket(JSONObject jsonTicket) {
        return new ServerDefaultTicket(jsonTicket, this);
    }

    public Ticket convertTicket(Ticket ticket) {
        return new ServerDefaultTicket(ticket);
    }

    public Coordinates getCoordinates(double x, Integer y) {
        return new ServerDefaultCoordinates(x, y);
    }

    public Coordinates getCoordinates(JSONObject jsonCoordinates) {
        return new ServerDefaultCoordinates(jsonCoordinates);
    }

    public Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        return new ServerDefaultPerson(weight, eyesColor, hairColor, country);
    }

    public Person getPerson(JSONObject jsonPerson) {
        return new ServerDefaultPerson(jsonPerson);
    }

    public Collection<Ticket> getTicketsCollection() {
        return new PriorityQueue<>();
    }

    public Coordinates getLeastCoordinates() {
        return new ServerDefaultCoordinates(-171, -235);
    }
}
