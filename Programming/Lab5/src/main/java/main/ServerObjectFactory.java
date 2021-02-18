package main;

import main.ticket.*;
import org.json.simple.JSONObject;

import java.time.ZonedDateTime;
import java.util.Collection;

/**
 * Interface of object factory for server application
 */

public interface ServerObjectFactory {

    /**
     * Ticket getter
     * @param id id in Object format that will be converted into id
     * @param time time in Object format that will be converted into time
     * @param name ticket name
     * @param coordinates ticket coordinates
     * @param price ticket price
     * @param discount ticket discount
     * @param refundable ticket refundable
     * @param type ticket type
     * @param person ticket person
     * @return ticket
     */

    Ticket getTicket(Object id, Object time, String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person);

    /**
     * Coordinates getter
     * @param x x coordinate
     * @param y y coordinate
     * @return coordinates of ticket
     */

    Coordinates getCoordinates(double x, Integer y);

    /**
     * Person getter
     * @param weight weight
     * @param eyesColor eye color
     * @param hairColor hair color
     * @param country country
     * @return person
     */

    Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country);

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

    Ticket convertTicket(Ticket ticket);
}
