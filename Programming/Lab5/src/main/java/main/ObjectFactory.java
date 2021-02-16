package main;

import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.PriorityQueue;

public abstract class ObjectFactory {
    public static CommandReader getCmdReader(CommandFactory commandFactory, CollectionManager collectionManager) throws FileNotFoundException {
        return new CmdCommandReader(commandFactory, collectionManager);
    }

    public static CommandReader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException {
        return new FileCommandReader(commandFactory, collectionManager, file);
    }

    public static Ticket getTicket(JSONObject jsonTicket) {
        return new DefaultTicket(jsonTicket);
    }

    public static Ticket getTicket(String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person) {
        return new DefaultTicket(name, coordinates, price, discount, refundable, type, person);
    }

    public static Coordinates getCoordinates(double x, Integer y) {
        return new Coordinates(x, y);
    }

    public static Coordinates getCoordinates(JSONObject jsonCoordinates) {
        return new Coordinates(jsonCoordinates);
    }

    public static Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        return new Person(weight, eyesColor, hairColor, country);
    }

    public static Person getPerson(JSONObject jsonPerson) {
        return new Person(jsonPerson);
    }

    public static Collection<Ticket> getTicketsCollection() {
        return new PriorityQueue<Ticket>();
    }
}
