package commands;

import manager.CollectionManager;
import manager.CommandFactory;
import manager.CommandReader;

/**
 * Class of remove_first command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveFirstCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;


    public RemoveFirstCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Removing the first element has started");
        try {
            collectionManager.removeFirst();
            //System.out.println("The first element has been successfully removed");
        } catch (Exception e) {
            System.err.println("Error got while removing the first element");
        }
    }

    public static String strConvert() {
        return "remove_first: remove first ticket from the collection.";
    }
}
