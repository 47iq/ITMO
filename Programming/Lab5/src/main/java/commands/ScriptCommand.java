package commands;

import main.*;

/**
 * Interface of the script executing command
 */

public interface ScriptCommand extends Command{

    /**
     * Method that executes command
     * @param collectionManager manager of the collection
     * @param reader command reader
     * @param arg argument
     * @param commandFactory command factory
     * @param clientObjectFactory object factory
     */
    void execute(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory, ClientObjectFactory clientObjectFactory);
}
