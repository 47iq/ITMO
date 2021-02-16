package commands;

import main.AbstractQueueManager;
import main.CollectionManager;
import main.CommandReader;

/**
 * Class of max_by_coordinates command
 * @autor 47iq
 * @version 1.0
 */

public class MaxByCoordinatesCommand implements Command {

    /**
     * Collection's manager
     */

    private final CollectionManager collectionManager;


    public MaxByCoordinatesCommand(CollectionManager collectionManager, CommandReader reader, String arg) {
        this.collectionManager = collectionManager;
    }

    public void execute() {
        //System.out.println("Getting the max by coordinates ticket");
        try{
            collectionManager.maxByCoordinates();
            //System.out.println("The max by coordinates ticket has been successfully displayed");
        } catch (Exception e) {
            System.err.println("Error got while displaying the max by coordinates element");
        }
    }

    public static String strConvert() {
        return "max_by coordinates: get the ticket, biggest by coordinates.";
    }
}
