package commands;

import main.CollectionManager;
import main.CommandReader;
import main.Messenger;

/**
 * Interface of command that uses messenger
 */

public interface MessagingCommand extends Command{

    /**
     * Method that executes command
     * @param collectionManager manager of the collection
     * @param reader command reader
     * @param arg argument of the command
     * @param messenger messenger
     */
    void execute(CollectionManager collectionManager, CommandReader reader, String arg, Messenger messenger);
}
