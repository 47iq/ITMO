package main;

import main.ticket.*;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class for creation of objects for client application
 */

public class DefaultClientObjectFactory implements ClientObjectFactory {

    public CommandReader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException {
        return new FileCommandReader(commandFactory, collectionManager, file, this);
    }

    public Ticket getTicket(String name, double x, Integer y, int price, double discount, Boolean refundable, TicketType type, Long weight,
                            EyesColor eyesColor, HairColor hairColor, Country country) {
        return new ClientTemplateTicket(name, x, y, price, discount, refundable, type, weight, eyesColor, hairColor, country, this);
    }

    public Coordinates getCoordinates(double x, Integer y) {
        return new ClientTemplateCoordinates(x, y);
    }

    public Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        return new ClientTemplatePerson(weight, eyesColor, hairColor, country);
    }
}
