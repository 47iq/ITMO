package commands;

import main.AbstractQueueManager;
import main.CollectionManager;
import main.CommandFactory;
import main.CommandReader;

/**
 * Class of show command
 * @autor 47iq
 * @version 1.0
 */

public class ShowCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;


    public ShowCommand(CollectionManager collectionManager, CommandReader reader, String arg, CommandFactory commandFactory) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Displaying tickets");
        try {
            collectionManager.displayElements();
            //System.out.println("Tickets have been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying tickets");
        }
    }

    public static String strConvert() {
        return "show: get the collection's elements.";
    }
}
