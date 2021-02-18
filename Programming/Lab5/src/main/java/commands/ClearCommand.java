package commands;

import main.CollectionManager;
import main.CommandReader;

/**
 * Class of clear command
 * @autor 47iq
 * @version 1.0
 */

public class ClearCommand implements SimpleCommand {

    public ClearCommand() {
    }

    public void execute(CollectionManager collectionManager, CommandReader reader, String arg) {
        //System.out.println("Clearing the collection has started");
        try {
            collectionManager.clear();
            //System.out.println("Collection has been successfully cleared");
        } catch (Exception e) {
            System.err.println("Error got while clearing collection");
        }
    }
}
