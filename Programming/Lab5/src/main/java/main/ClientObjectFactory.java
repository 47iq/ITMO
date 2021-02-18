package main;

import main.ticket.*;

import java.io.File;
import java.io.FileNotFoundException;

public interface ClientObjectFactory {
    Ticket getTicket(String name, double x, Integer y, int price, double discount, Boolean refundable, TicketType type, Long weight,
                     EyesColor eyesColor, HairColor hairColor, Country country);
    Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country);

    Coordinates getCoordinates(double x, Integer y);
    CommandReader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException;
}
