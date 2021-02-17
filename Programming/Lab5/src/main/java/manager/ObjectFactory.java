package manager;

import org.json.simple.JSONObject;
import ticket.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.PriorityQueue;

public abstract class ObjectFactory {

    public static CommandReader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException {
        return new FileCommandReader(commandFactory, collectionManager, file);
    }

    public static Ticket getTicket(JSONObject jsonTicket) {
        return new DefaultTicket(jsonTicket);
    }

    public static Ticket getTicket(String name, double x, Integer y, int price, double discount, Boolean refundable, TicketType type, Long weight,
                                   EyesColor eyesColor, HairColor hairColor, Country country) {
        return new DefaultTicket(name, x, y, price, discount, refundable, type, weight, eyesColor, hairColor, country);
    }

    public static Coordinates getCoordinates(double x, Integer y) {
        return new DefaultCoordinates(x, y);
    }

    public static Coordinates getCoordinates(JSONObject jsonCoordinates) {
        return new DefaultCoordinates(jsonCoordinates);
    }

    public static Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        return new DefaultPerson(weight, eyesColor, hairColor, country);
    }

    public static Person getPerson(JSONObject jsonPerson) {
        return new DefaultPerson(jsonPerson);
    }

    public static Collection<Ticket> getTicketsCollection() {
        return new PriorityQueue<>();
    }

    public static Coordinates getLeastCoordinates() {
        return new DefaultCoordinates(-171, -235);
    }
}
