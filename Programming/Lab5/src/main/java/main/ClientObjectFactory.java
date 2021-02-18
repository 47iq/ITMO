package main;

import main.ticket.*;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Object factory used by client application
 */

public interface ClientObjectFactory {

    /**
     * {@link Person} creator
     * @param weight person weight
     * @param eyesColor person eye color
     * @param hairColor person hair color
     * @param country person nationality
     * @return Person
     */

    Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country);

    /**
     * {@link Coordinates} creator
     * @param x x coordinate
     * @param y y coordinate
     * @return Coordinates
     */

    Coordinates getCoordinates(double x, Integer y);

    /**
     * CommandReader getter
     * @param commandFactory factory of commands
     * @param collectionManager manager of ticket collection
     * @param file file we want to read from
     * @return command reader for the file
     * @throws FileNotFoundException if there's no file with this name
     */

    CommandReader getFileReader(CommandFactory commandFactory, CollectionManager collectionManager, File file) throws FileNotFoundException;

    DefaultTicket getDefaultTicket();

    DefaultCoordinates getDefaultCoordinates();

    DefaultPerson getDefaultPerson();
}
