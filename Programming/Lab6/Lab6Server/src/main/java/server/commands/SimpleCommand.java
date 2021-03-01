package server.commands;

import common.Response;
import server.collection.CollectionManager;
import server.ObjectFactory;

/**
 * Interface of the regular command
 */

public interface SimpleCommand extends Command{

    /**
     * Method that executes the command
     * @param collectionManager manager of the collection
     * @param arg argument of the command
     */

    Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory);
}
