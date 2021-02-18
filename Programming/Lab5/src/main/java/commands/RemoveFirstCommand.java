package commands;

import main.CollectionManager;
import main.CommandReader;

/**
 * Class of remove_first command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveFirstCommand implements SimpleCommand {

    public RemoveFirstCommand() {

    }

    public void execute(CollectionManager collectionManager, CommandReader reader, String arg) {
        //System.out.println("Removing the first element has started");
        try {
            collectionManager.removeFirst();
            //System.out.println("The first element has been successfully removed");
        } catch (Exception e) {
            System.err.println("Error got while removing the first element");
        }
    }
}
