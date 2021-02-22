package commands;

import main.CollectionManager;
import main.CommandReader;

/**
 * Interface of the regular command
 */

public interface SimpleCommand extends Command{

    /**
     * Method that executes the command
     * @param collectionManager manager of the collection
     * @param reader command reader
     * @param arg argument of the command
     */

    void execute(CollectionManager collectionManager, CommandReader reader, String arg);
}
