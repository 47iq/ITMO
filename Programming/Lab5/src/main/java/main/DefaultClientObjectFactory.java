package main;

import main.ticket.*;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Class for creation of objects for client application
 */

public class DefaultClientObjectFactory implements ClientObjectFactory {

    public Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        return new ClientTemplatePerson(weight, eyesColor, hairColor, country);
    }

    public Coordinates getCoordinates(double x, Integer y) {
        return new ClientTemplateCoordinates(x, y);
    }

    public CommandReader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException {
        return new FileCommandReader(commandFactory, collectionManager, file, this);
    }

    public DefaultTicket getDefaultTicket() {
        return new ClientTemplateTicket();
    }

    public DefaultCoordinates getDefaultCoordinates() {
        return new ClientTemplateCoordinates();
    }

    public DefaultPerson getDefaultPerson() {
        return new ClientTemplatePerson();
    }
}
