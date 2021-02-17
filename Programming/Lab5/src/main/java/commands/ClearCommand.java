package commands;

import manager.CollectionManager;
import manager.CommandFactory;
import manager.CommandReader;

/**
 * Class of clear command
 * @autor 47iq
 * @version 1.0
 */

public class ClearCommand implements Command {
    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;

    /**
     * Constructor of the clear command
     * @param collectionManager collection's manager
     */

    public ClearCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Clearing the collection has started");
        try {
            collectionManager.clear();
            //System.out.println("Collection has been successfully cleared");
        } catch (Exception e) {
            System.err.println("Error got while clearing collection");
        }
    }

    public static String strConvert() {
        return "clear: clear the collection.";
    }
}
