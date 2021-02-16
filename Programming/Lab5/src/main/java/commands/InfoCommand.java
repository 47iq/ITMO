package commands;

import main.AbstractQueueManager;
import main.CollectionManager;
import main.CommandReader;

/**
 * Class of info command
 * @autor 47iq
 * @version 1.0
 */

public class InfoCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;


    public InfoCommand(CollectionManager collectionManager, CommandReader reader, String arg) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Info displaying has started.");
        try {
            collectionManager.displayInfo();
            //System.out.println("Info displaying has been completed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the info");
        }
    }

    public static String strConvert() {
        return "info : get information about the collection.";
    }

}
